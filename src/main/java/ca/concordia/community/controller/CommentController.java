package ca.concordia.community.controller;

import ca.concordia.community.dto.CommentDto;
import ca.concordia.community.mapper.CommentMapper;
import ca.concordia.community.model.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.enterprise.inject.spi.Bean;

/**
 * Created by Hanford Wu on 2020-07-24 10:38 p.m.
 */
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;


    @PostMapping("/comment")
    public Object post(@RequestBody
                       CommentDto commentDto){
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator();
        commentMapper.insert(comment);
    }




}
