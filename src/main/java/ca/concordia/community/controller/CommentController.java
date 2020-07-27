package ca.concordia.community.controller;

import ca.concordia.community.dto.CommentDto;
import ca.concordia.community.dto.ResultDto;
import ca.concordia.community.enums.CommentTypeEnum;
import ca.concordia.community.mapper.CommentMapper;
import ca.concordia.community.model.Comment;
import ca.concordia.community.model.User;
import ca.concordia.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Hanford Wu on 2020-07-24 10:38 p.m.
 */
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;




    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody
                       CommentDto commentDto,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            return ResultDto.errorOf(2005, "You are not login!");
        }

        if (commentDto == null || commentDto.getContent() == null || commentDto.getContent().equals("")){
            return ResultDto.errorOf(2008, "Comment content cannot be empty");
        }

        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        commentService.insert(comment,user);

        return ResultDto.okOf(null);
    }


    @GetMapping("/comment/{id}")
    @ResponseBody
    public ResultDto comments(@PathVariable int id){
        List<CommentDto> commentDtos = commentService.listByQuestionId(id, CommentTypeEnum.COMMENT_TYPE_ENUM.getType());
        return ResultDto.okOf(commentDtos);
    }




}
