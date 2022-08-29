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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    public String addGoods(Goods goods, @RequestPart("goodsImg") MultipartFile gimg) throws IOException {

        //  先保存图片
        // 先获取本地保存文件的路径
        String saveFileDir = "/home/chenchiyu/classRoom/Tianlai03/temp";
        // 拼接路径
        String saveFileName = UUID.randomUUID() + gimg.getOriginalFilename();
        // 保存文件到本地
        gimg.transferTo(new File(saveFileDir, saveFileName));

        // 设置文件的保存路径到goods对象中
        goods.setGimg(gimg.getOriginalFilename());
        boolean res = goodsService.insert(goods);

        return "redirect:/dataList.html";

    }

    @PutMapping("/product")
    public String updateGoods(Goods goods) {

        boolean res = goodsService.updateByPrimaryKey(goods);

        return "redirect:/dataList.html";

    }

    @DeleteMapping("/product/{gid}")
    public String delGoodsById(@PathVariable("gids") Integer[] gids, Model model) {
        String errInfo = "删除成功";
        for (Integer gid : gids) {
            boolean res = goodsService.deleteByGid(gid);
            if (!res) {
                errInfo = "删除失败";
            }
        }
        model.addAttribute("errInfo", errInfo);
//        return "forward:/dataList.html"; //请求转发
        return "redirect:/dataList.html";
    }


}
