package ca.concordia.community.mapper;

import ca.concordia.community.model.TUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Hanford Wu on 2020-07-19 9:53 p.m.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into t_user (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(TUser tUser);


}
