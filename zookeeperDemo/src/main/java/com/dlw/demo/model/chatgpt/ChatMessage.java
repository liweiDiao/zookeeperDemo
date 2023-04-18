package com.dlw.demo.model.chatgpt;

/**
 * @Description 消息信息
 * @Author diaoliwei
 * @Date 2023/4/15 16:39
 */
public class ChatMessage {

    /**
     * 消息角色
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
