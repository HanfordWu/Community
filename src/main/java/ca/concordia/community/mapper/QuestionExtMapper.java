package ca.concordia.community.mapper;

import ca.concordia.community.dto.QuestionDto;
import ca.concordia.community.model.Question;

import java.util.List;

/**
 * Created by Hanford Wu on 2020-07-24 10:00 p.m.
 */
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(QuestionDto question);
}
