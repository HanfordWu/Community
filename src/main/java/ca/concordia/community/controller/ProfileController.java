package ca.concordia.community.controller;

import ca.concordia.community.dto.PaginationDto;
import ca.concordia.community.model.User;
import ca.concordia.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hanford Wu on 2020-07-24 1:01 p.m.
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable() String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page", defaultValue = "1") Integer page,
                          @RequestParam(name="size", defaultValue = "10") Integer size
    ){

        if ("questions".equals(action)){
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "MyQuestion");
        } else if ("replies".equals(action)){
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "My replies");
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            return "redirect:/";
        }


        PaginationDto paginationDto = questionService.listQuestion(user.getId(), page, size);

        model.addAttribute("questions", paginationDto);


        return "profile";
    }
}
