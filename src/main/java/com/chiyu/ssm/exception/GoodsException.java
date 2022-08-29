package com.chiyu.ssm.exception;

public class GoodsException extends RuntimeException{
    public GoodsException() {
    }

    public GoodsException(String message) {
        super(message);
    }

    public GoodsException(Throwable cause) {
        super(cause);
    }
}
