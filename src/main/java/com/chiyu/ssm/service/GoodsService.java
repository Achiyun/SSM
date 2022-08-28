package com.chiyu.ssm.service;

import com.chiyu.ssm.entity.Goods;
import com.chiyu.ssm.vo.PageVo;
import com.chiyu.ssm.vo.SearchVo;

public interface GoodsService {

    /**
     * 根据参数查询商品的分页数据
     *
     * @param searchVo 查询数据的参数
     * @return 分页对象
     */
    PageVo<Goods> selectGoodsByPageByParam(SearchVo searchVo, Integer page, Integer limit);


    boolean deleteByPrimaryKey(Integer gid);

}
