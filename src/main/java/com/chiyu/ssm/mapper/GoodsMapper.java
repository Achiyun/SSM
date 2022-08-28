package com.chiyu.ssm.mapper;

import com.chiyu.ssm.entity.Goods;
import com.chiyu.ssm.vo.SearchVo;

import java.util.List;

public interface GoodsMapper {
    boolean deleteByPrimaryKey(Integer gid);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer gid);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);

    List<Goods> selectGoodsByPageByParam(SearchVo searchVo);
}