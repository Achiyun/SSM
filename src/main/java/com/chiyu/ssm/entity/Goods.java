package com.chiyu.ssm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Goods {
    private Integer gid;

    private String gname;

    private String gimg;

    private BigDecimal gprice;

    private Integer gstock;

    private LocalDateTime maintaindate;

    private String classify;

    private String gdescribe;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public BigDecimal getGprice() {
        return gprice;
    }

    public void setGprice(BigDecimal gprice) {
        this.gprice = gprice;
    }

    public Integer getGstock() {
        return gstock;
    }

    public void setGstock(Integer gstock) {
        this.gstock = gstock;
    }

    public LocalDateTime getMaintaindate() {
        return maintaindate;
    }

    public void setMaintaindate(LocalDateTime maintaindate) {
        this.maintaindate = maintaindate;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getGdescribe() {
        return gdescribe;
    }

    public void setGdescribe(String gdescribe) {
        this.gdescribe = gdescribe;
    }
}