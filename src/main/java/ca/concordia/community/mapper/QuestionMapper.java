package ca.concordia.community.mapper;

import ca.concordia.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Hanford Wu on 2020-07-21 5:17 p.m.
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator}, #{tag})" )
    void create(Question question);
}
