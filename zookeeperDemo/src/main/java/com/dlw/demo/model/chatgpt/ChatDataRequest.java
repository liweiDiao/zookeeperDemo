package com.dlw.demo.model.chatgpt;

import java.util.List;

/**
 * @Description 发送的请求的参数实体
 * @Author diaoliwei
 * @Date 2023/4/15 16:47
 */
public class ChatDataRequest {

    /**
     * 选择使用的模型
     */
    private String model;

    /**
     * 发送的消息列表
     */
    private List<ChatMessage> messages;

    /**
     * 精度，参数从0-2，越低表示越精准，越高表示越广发，回答的内容重复率越低
     */
    private Double temperature;

    /**
     * 回复条数
     */
    private Integer nums;

    /**
     * 是否流式处理
     */
    private Boolean stream;

    private List<String> stop;

    /**
     * 生成的答案允许的最大token数
     */
    private Integer maxTokens;

    /**
     * 对话用户
     */
    private String user;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }

    public List<String> getStop() {
        return stop;
    }

    public void setStop(List<String> stop) {
        this.stop = stop;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
