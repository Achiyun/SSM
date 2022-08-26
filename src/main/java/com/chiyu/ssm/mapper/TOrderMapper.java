package com.chiyu.ssm.mapper;

import java.util.List;

import com.chiyu.ssm.entity.TOrder;
import org.apache.ibatis.annotations.Param;

public interface TOrderMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("gid") Integer gid);

    int insert(TOrder record);

    TOrder selectByPrimaryKey(@Param("uid") Integer uid, @Param("gid") Integer gid);

    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);
}