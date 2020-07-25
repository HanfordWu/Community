package ca.concordia.community.controller;

import ca.concordia.community.dto.QuestionDto;
import ca.concordia.community.mapper.QuestionMapper;
import ca.concordia.community.model.Question;
import ca.concordia.community.model.QuestionExample;
import ca.concordia.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hanford Wu on 2020-07-19 11:30 p.m.
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam String title,
                            @RequestParam String description,
                            @RequestParam String tags,
                            @RequestParam String id,
                            HttpServletRequest request,
                            Model model) {


        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tags", tags);

        if (title==null || title.equals("")){
            model.addAttribute("error", "Title cannot be null");
            return "publish";
        }
        if (description==null || description==""){
            model.addAttribute("error", "Description cannot be null");
            return "publish";
        }
        if (tags==null || tags.equals("")){
            model.addAttribute("error", "Tags cannot be null");
            return "publish";
        }





        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            model.addAttribute("error", "You are not logged in");
            return "publish";
        }




        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tags);



        question.setCreator(user.getId());
        question.setCommentCount(0);
        question.setViewCount(0);

        if (id != null && !"".equals(id)){
            question.setGmtModified(System.currentTimeMillis());
            question.setId(Integer.parseInt(id));
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question, example);
        }else {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }



        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable int id,
                       Model model){
        Question questionDto = questionMapper.selectByPrimaryKey(id);
//        model.addAttribute("question", questionDto);
        model.addAttribute("title", questionDto.getTitle());
        model.addAttribute("description", questionDto.getDescription());
        model.addAttribute("tag", questionDto.getTag());
        model.addAttribute("questionId", id);
        return "publish";
    }


}
