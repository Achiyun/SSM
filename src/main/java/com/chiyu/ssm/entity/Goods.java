package com.chiyu.ssm.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Goods {
    private Integer gid;
    private String gname;
    private String gimg;
    private BigDecimal gprice;
    private Integer gstock;
    private LocalDate maintaindate;
    private String classify;
    private String gdescribe;

}