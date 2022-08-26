package com.chiyu.ssm.service;

import com.chiyu.ssm.entity.User;

public interface UserService {

    User selectUserByName(String uname);
}
