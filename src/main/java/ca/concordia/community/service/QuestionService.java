package ca.concordia.community.service;

import ca.concordia.community.dto.PaginationDto;
import ca.concordia.community.dto.QuestionDto;
import ca.concordia.community.dto.QuestionQueryDto;
import ca.concordia.community.exception.CustomizeException;
import ca.concordia.community.mapper.QuestionExtMapper;
import ca.concordia.community.mapper.QuestionMapper;
import ca.concordia.community.mapper.UserMapper;
import ca.concordia.community.model.Question;
import ca.concordia.community.model.QuestionExample;
import ca.concordia.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hanford Wu on 2020-07-22 12:30 a.m.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;


    public PaginationDto<QuestionDto> listQuestion(Integer page,
                                      Integer size, String search) {
        if (search!=null && !"".equals(search)){
            search = search.replace(' ', '|');
        }




        Integer offset = size * (page - 1);
        PaginationDto<QuestionDto> paginationDto = new PaginationDto<>();
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDto> questionDtos=new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        paginationDto.setData(questionDtos);
        QuestionQueryDto questionQueryDto = new QuestionQueryDto();
        questionQueryDto.setSearch(search);
        paginationDto.setPagination(questionExtMapper.countBySearch(questionQueryDto), page, size);
        return paginationDto;
    }

    public PaginationDto<QuestionDto> listQuestion(int id,
                                      Integer page,
                                      Integer size) {


        Integer offset = size * (page - 1);
        PaginationDto<QuestionDto> paginationDto = new PaginationDto<>();
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(id);
        example1.setOrderByClause("gmt_create desc");
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));
        List<QuestionDto> questionDtos=new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        paginationDto.setData(questionDtos);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);

        paginationDto.setPagination((int) questionMapper.countByExample(example),page, size);


        return paginationDto;
    }

    public QuestionDto getById(int id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException(2006, "The question doesn't exist, try anther one!");
        }


        User user=userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDto questionDto = new QuestionDto();

        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(user);
        return questionDto;

    }

    public void incView(int id) {

        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
