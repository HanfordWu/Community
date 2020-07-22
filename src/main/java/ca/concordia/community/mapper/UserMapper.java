package ca.concordia.community.mapper;

import ca.concordia.community.model.TUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Hanford Wu on 2020-07-19 9:53 p.m.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into t_user (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(TUser tUser);

    @Select("select * from t_user where account_id=#{accountId}")
    @Options(useGeneratedKeys=true, keyProperty="id")
    TUser findUserByAccountId(TUser user);

    @Select("select * from t_user where id=#{id}")
    @Options(useGeneratedKeys=true, keyProperty="id")
    TUser findUserById(Long id);
}
