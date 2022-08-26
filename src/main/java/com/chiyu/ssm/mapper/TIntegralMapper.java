package com.chiyu.ssm.mapper;


import com.chiyu.ssm.entity.TIntegral;

import java.util.List;

public interface TIntegralMapper {
    int deleteByPrimaryKey(Integer itid);

    int insert(TIntegral record);

    Integer selectByPrimaryKey(Integer itid);

    List<TIntegral> selectAll();

    int updateByPrimaryKey(TIntegral record);
}