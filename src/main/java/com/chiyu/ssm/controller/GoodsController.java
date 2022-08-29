package com.chiyu.ssm.controller;

import com.chiyu.ssm.entity.Goods;
import com.chiyu.ssm.exception.GoodsException;
import com.chiyu.ssm.service.GoodsService;
import com.chiyu.ssm.vo.PageVo;
import com.chiyu.ssm.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Slf4j
@Controller
public class GoodsController {

    @Value("${goods.imgDir}")
    private String uploadFileDir;
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

    // 提供一个本地文件读取的接口
//    @GetMapping("/{fileName}")
//    public void showImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
//        // 获取保存图片的目录
//        File saveFileDir = new File(uploadFileDir);
//        if (!saveFileDir.exists()) {
//           boolean res = saveFileDir.mkdirs();
//        }
//
//        File filePath = new File(saveFileDir, fileName);
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
//        // 准备缓存数组
//        byte[] buff = new byte[in.available()];
//        in.read(buff);
//        response.getOutputStream().write(buff);
//    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> showImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        // 获取保存图片的目录
        File saveFileDir = new File(uploadFileDir);
        if (!saveFileDir.exists()) {
            boolean res = saveFileDir.mkdirs();
        }

        File filePath = new File(saveFileDir, fileName);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
        // 准备缓存数组
        byte[] buff = new byte[in.available()];
        in.read(buff);

        HttpStatus status = HttpStatus.OK;

        // 2. 响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        // 直接将准备好的数据封装为一个响应对象
        return new ResponseEntity<>(buff, status);
    }


    /**
     * 添加商品
     *
     * @param goods
     * @param gimg
     * @return
     * @throws IOException
     */
    @PostMapping("/product")
    public String addGoods(Goods goods, @RequestPart("goodsImg") MultipartFile gimg) throws IOException {

        //  先保存图片
        // 先获取本地保存文件的路径
        File saveFileDir = new File(uploadFileDir);
        if (!saveFileDir.exists()) {
            boolean res = saveFileDir.mkdirs();
        }
        // 拼接路径
        String saveFileName = UUID.randomUUID() + gimg.getOriginalFilename();
        // 保存文件到本地
        gimg.transferTo(new File(saveFileDir, saveFileName));

        // 设置文件的保存路径到goods对象中
        goods.setGimg("download" + gimg.getOriginalFilename());
        boolean res = goodsService.insert(goods);

        return "redirect:/dataList.html";

    }

    @PutMapping("/product")
    public String updateGoods(Goods goods) {

        boolean res = goodsService.updateByPrimaryKey(goods);

        return "redirect:/dataList.html";

    }

    /**
     * 删除商品
     *
     * @param gid
     * @param model
     * @return
     */
    @DeleteMapping("/product/{gid}")
    public String delGoodsById(@PathVariable("gid") Integer gid, Model model) {
        String errInfo = "删除成功";
        gid = 1024;
        if (!goodsService.deleteByGid(gid)) {
            errInfo = "删除失败";
            throw new GoodsException("没有找到要删除的商品");
        }
        model.addAttribute("errInfo", errInfo);
//        return "forward:/dataList.html"; //请求转发
        return "redirect:/dataList.html";
    }

    @DeleteMapping("/products/{gid}")
    public String delGoodsById(@PathVariable("gid") Integer[] gids, Model model) {
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
