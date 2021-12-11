package com.dlw.demo.model;

/**
 * @Description
 * @Author diaoliwei
 * @Date 2021/1/20 18:43
 */
public class ResponseDTO {

    private int code;

    private String msg;

    private int status;

    private String contentType;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
