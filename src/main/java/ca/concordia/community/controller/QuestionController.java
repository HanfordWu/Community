package ca.concordia.community.controller;

import ca.concordia.community.dto.CommentDto;
import ca.concordia.community.dto.QuestionDto;
import ca.concordia.community.enums.CommentTypeEnum;
import ca.concordia.community.mapper.QuestionExtMapper;
import ca.concordia.community.model.Question;
import ca.concordia.community.service.CommentService;
import ca.concordia.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by Hanford Wu on 2020-07-24 3:27 p.m.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @GetMapping("question/{id}")
    public String question(@PathVariable int id,
                           Model model){
        QuestionDto questionDto=questionService.getById(id);
        questionService.incView(id);

        List<CommentDto> comments = commentService.listByQuestionId(id, CommentTypeEnum.QUESTION_TYPE_ENUM.getType());
        questionDto.setTag(questionDto.getTag().replace(' ', '|'));
        List<Question> questions = questionExtMapper.selectRelated(questionDto);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", questions);
        model.addAttribute("question", questionDto);
        return "question";
    }
}
