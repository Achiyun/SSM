package com.chiyu.ssm.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
// 生成链式语法
@Accessors(chain = true)
public class PageVo<T> {

    private List<T> dataList;
    private Integer pages;
    private Long total;
    private Integer currentPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    // 记录查询的参数， 用于回显数据
    private SearchVo searchVo;
}
