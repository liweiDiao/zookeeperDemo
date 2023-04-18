package com.dlw.demo.model.chatgpt;

/**
 * @Description 接收ChatGPT返回的数据
 * @Author diaoliwei
 * @Date 2023/4/18 15:34
 */
public class ChatCompletionChoice {

    private Integer index;

    private ChatMessage message;

    private String finishReason;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
}
