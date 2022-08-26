package com.chiyu.ssm.vo;

import lombok.Data;

// 封装登陆参数的实体
@Data
public class LoginVo {
    private String account;
    private String password;
    private String checkCode;
    private boolean saveMe;
}
