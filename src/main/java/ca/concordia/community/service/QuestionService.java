package ca.concordia.community.service;

import ca.concordia.community.dto.QuestionDto;
import ca.concordia.community.mapper.QuestionMapper;
import ca.concordia.community.mapper.UserMapper;
import ca.concordia.community.model.Question;
import ca.concordia.community.model.TUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanford Wu on 2020-07-22 12:30 a.m.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    public List<QuestionDto> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDto> questionDtos=new ArrayList<>();
        for (Question question : questions) {
            TUser user=userMapper.findUserById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }
}
