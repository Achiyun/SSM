package com.chiyu.ssm.entity;

import lombok.Data;

@Data
public class User {
    private Integer uid;
    private String account;
    private String password;
    private String email;
}