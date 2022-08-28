package com.chiyu.ssm.controller;

import com.chiyu.ssm.entity.Goods;
import com.chiyu.ssm.service.GoodsService;
import com.chiyu.ssm.vo.PageVo;
import com.chiyu.ssm.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/product")
    public String addGoods(Goods goods) {
        boolean res = goodsService.insert(goods);

        return "redirect:/dataList.html";

    }
    @PutMapping ("/product")
    public String updateGoods(Goods goods) {

        boolean res = goodsService.updateByPrimaryKey(goods);

        return "redirect:/dataList.html";

    }

    @DeleteMapping("/product/{gid}")
    public String delGoodsById(@PathVariable Integer gid, Model model) {
        String errInfo = "删除成功";
        if (!goodsService.deleteByGid(gid)) {
            errInfo = "删除失败";
        }
        model.addAttribute("errInfo", errInfo);
//        return "forward:/dataList.html"; //请求转发
        return "redirect:/dataList.html";
    }


}
