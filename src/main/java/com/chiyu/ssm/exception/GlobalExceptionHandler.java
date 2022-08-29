package com.chiyu.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        System.out.println("---------------------GlobalExceptionHandler------------------------------");
        ex.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", ex.getMessage());

        // 判断抛出的异常
        if (ex instanceof GoodsException) {
            modelAndView.setViewName("404");

        } else {
            modelAndView.setViewName("5xx");

        }

        //打印异常信息
        return modelAndView;
    }
}
