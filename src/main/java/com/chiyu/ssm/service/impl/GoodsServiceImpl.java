package com.chiyu.ssm.service.impl;

import com.chiyu.ssm.entity.Goods;
import com.chiyu.ssm.mapper.GoodsMapper;
import com.chiyu.ssm.service.GoodsService;
import com.chiyu.ssm.vo.PageVo;
import com.chiyu.ssm.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageVo<Goods> selectGoodsByPageByParam(SearchVo searchVo, Integer page, Integer limit) {

        // 开启分页
        PageHelper.startPage(page, limit);

        // 查询数据
        List<Goods> goodsList = goodsMapper.selectGoodsByPageByParam(searchVo);

        System.out.println(goodsList);

        // 获取分页数据
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);

        return new PageVo<Goods>().setDataList(goodsList)
                .setPages(pageInfo.getPages())
                .setTotal(pageInfo.getTotal())
                .setCurrentPage(page)
                .setFirstPage(pageInfo.isIsFirstPage())
                .setLastPage(pageInfo.isIsLastPage())
                .setSearchVo(searchVo);
    }

    @Override
    public boolean deleteByGid(Integer gid) {
        return goodsMapper.deleteByPrimaryKey(gid) > 0;
    }

    @Override
    public boolean insert(Goods goods) {
        return goodsMapper.insert(goods) > 0;
    }

    @Override
    public boolean updateByPrimaryKey(Goods goods) {
        return goodsMapper.updateByPrimaryKey(goods) > 0;
    }


}
