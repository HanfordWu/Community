package ca.concordia.community.mapper;

import ca.concordia.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Hanford Wu on 2020-07-21 5:17 p.m.
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description,gmt_create,gmt_modified,creator,tag, view_count, comment_count) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator}, #{tag}, #{viewCount}, #{commentCount})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
