package com.dlw.demo.model.chatgpt;

import org.springframework.http.HttpMethod;

/**
 * @Description 接收请求返回的信息
 * @Author diaoliwei
 * @Date 2023/4/15 16:54
 */
public class ExecuteRet {

    /**
     * 操作是否成功
     */
    private final boolean success;

    /**
     * 返回的内容
     */
    private final String respStr;

    /**
     * 请求的地址
     */
    private final HttpMethod method;

    /**
     * statusCode
     */
    private final int statusCode;

    public ExecuteRet(boolean success, String respStr, HttpMethod method, int statusCode) {
        this.success = success;
        this.respStr = respStr;
        this.method = method;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExecuteRet { success=").append(success).append(", respStr=").append(respStr).append(", statusCode=").append(statusCode);
        sb.append(" } ");
        return sb.toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRespStr() {
        return respStr;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
