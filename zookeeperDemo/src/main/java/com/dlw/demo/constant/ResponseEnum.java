package com.dlw.demo.constant;

/**
 * @Description 限流
 * @Author diaoliwei
 * @Date 2021/1/20 18:31
 */
public enum ResponseEnum {

    OK(200, "success"),

    RATE_LIMIT(401, "访问次数受限"),

    EXCEPTION(500, "EXCEPTION"),

    QUERY_USER_FAILED(601, "查询用户失败");

    private int code;

    private String desc;

    ResponseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
