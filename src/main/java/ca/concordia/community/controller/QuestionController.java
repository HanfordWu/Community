package ca.concordia.community.controller;

import ca.concordia.community.dto.QuestionDto;
import ca.concordia.community.mapper.QuestionMapper;
import ca.concordia.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Hanford Wu on 2020-07-24 3:27 p.m.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable int id,
                           Model model){
        QuestionDto questionDto=questionService.getById(id);
        questionService.incView(id);
        model.addAttribute("question", questionDto);
        return "question";
    }
}
