package com.connectu.connectuapi.exception.thread;

public class InsertThreadException extends RuntimeException{

    private Integer code;
    private String message;
    public InsertThreadException() {

    }

    public InsertThreadException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public InsertThreadException(Integer code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
