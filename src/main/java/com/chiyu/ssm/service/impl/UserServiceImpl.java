package com.chiyu.ssm.service.impl;

import com.chiyu.ssm.entity.User;
import com.chiyu.ssm.mapper.UserMapper;
import com.chiyu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectUserByName(String uname) {
        return userMapper.selectUserByName(uname);
    }


}
