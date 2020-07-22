package ca.concordia.community.controller;

import ca.concordia.community.dto.QuestionDto;
import ca.concordia.community.mapper.QuestionMapper;
import ca.concordia.community.model.Question;
import ca.concordia.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Hanford Wu on 2020-07-19 3:27 p.m.
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;




    @GetMapping("/")
    public String index(Model model){

        List<QuestionDto> questions=questionService.list();
        model.addAttribute("questions", questions);





        return "index";
    }
}
