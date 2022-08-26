package com.chiyu.ssm.mapper;

import com.chiyu.ssm.entity.UAccount;

import java.util.List;

public interface UAccountMapper {
    int deleteByPrimaryKey(Integer accid);

    int insert(UAccount record);

    UAccount selectByPrimaryKey(Integer accid);

    List<UAccount> selectAll();

    int updateByPrimaryKey(UAccount record);
}