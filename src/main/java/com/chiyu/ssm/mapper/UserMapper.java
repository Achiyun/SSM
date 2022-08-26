package com.chiyu.ssm.mapper;

import com.chiyu.ssm.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);
    int insert(User record);
    User selectByPrimaryKey(Integer uid);
    List<User> selectAll();
    int updateByPrimaryKey(User record);

    @Select("select * from user where account=#{uname}")
    User selectUserByName(String uname);
}