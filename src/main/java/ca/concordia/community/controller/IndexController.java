package ca.concordia.community.controller;

import ca.concordia.community.dto.PaginationDto;
import ca.concordia.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Hanford Wu on 2020-07-19 3:27 p.m.
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;




    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page", defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue = "10") Integer size
                        ){

        PaginationDto questions=questionService.listQuestion(page, size);
        model.addAttribute("questions", questions);





        return "index";
    }
}
