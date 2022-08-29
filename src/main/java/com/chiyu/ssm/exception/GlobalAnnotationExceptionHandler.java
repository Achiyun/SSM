package com.chiyu.ssm.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalAnnotationExceptionHandler {

    @ExceptionHandler(GoodsException.class)
    public String handleGoodsException(GoodsException ex){
        ex.printStackTrace();
        return "5xx";
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public String NoHandlerFoundException(NoHandlerFoundException ex){
        ex.printStackTrace();
        return "404";
    }
    @ExceptionHandler(ArithmeticException.class)
    public String handleArithmeticExceptionException(Exception ex){
        ex.printStackTrace();
        return "5xx";
    }
    @ExceptionHandler(Exception.class)
    public String handleGoodsException(Exception ex){
        ex.printStackTrace();
        return "5xx";
    }
}
