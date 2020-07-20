package ca.concordia.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Hanford Wu on 2020-07-19 11:30 p.m.
 */
@Controller
public class PublishController {


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
