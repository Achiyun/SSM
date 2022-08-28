package com.chiyu.ssm.controller;

import com.chiyu.ssm.entity.Goods;
import com.chiyu.ssm.service.GoodsService;
import com.chiyu.ssm.vo.PageVo;
import com.chiyu.ssm.vo.SearchVo;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/dataList.html")
    public String findAll(SearchVo searchVo,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "5") Integer limit,
                          Model model) {
        // 去数据库查询数据
        PageVo<Goods> pageVo = goodsService.selectGoodsByPageByParam(searchVo, page, limit);
        log.debug("我是searchVo-> {}", pageVo.getPages());

        model.addAttribute("pageVo", pageVo);

        return "dataList";
    }
}
