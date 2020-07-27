package ca.concordia.community.service;

import ca.concordia.community.dto.CommentDto;
import ca.concordia.community.enums.CommentTypeEnum;
import ca.concordia.community.enums.NotificationStatusEnum;
import ca.concordia.community.enums.NotificationTypeEnum;
import ca.concordia.community.exception.CustomizeException;
import ca.concordia.community.mapper.*;
import ca.concordia.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Hanford Wu on 2020-07-24 11:25 p.m.
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;




    @Transactional
    public void insert(Comment comment, User commentator) {
        if (comment.getParentId() == null || comment.getParentId() ==0){
            throw new CustomizeException(2001, "comment has no parent id");
        }
        if (comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(2002, "comment don't have a type!");
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT_TYPE_ENUM.getType())){
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(2003, "Comment you are replying Is Not Found!");
            }else {
                commentMapper.insert(comment);

                createNotify(comment, dbComment.getCommentator(), commentator.getName(), comment.getContent(), NotificationTypeEnum.REPLY_COMMENT);


            }
        }else{
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                throw new CustomizeException(2004, "Question not found");
            } else {
                commentMapper.insert(comment);
                question.setCommentCount(1);
                questionExtMapper.incCommentCount(question);

                createNotify(comment, question.getCreator(),commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION);
            }
        }
    }

    private void createNotify(Comment comment,
                              Integer receiver,
                              String notifierName,
                              String outerTitle,
                              NotificationTypeEnum notificationTypeEnum) {
        Notification notification = new Notification();

        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationTypeEnum.getType());

        notification.setOuterId(comment.getParentId());

        notification.setNotifier(comment.getCommentator());

        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setOuterTitle(outerTitle);

        notificationMapper.insert(notification);
    }

    public List<CommentDto> listByQuestionId(int id,
                                             Integer type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type);

        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0){
            return new ArrayList<>();
        }
        //to get creator of comments, no duplicate
        Set<Integer> commentators = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(new ArrayList<>(commentators));
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));

        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(comment.getCommentator()));
            return commentDto;
        }).collect(Collectors.toList());

        return commentDtos;
    }
}
