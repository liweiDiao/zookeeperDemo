package com.dlw.demo.model;

/**
 * @author diaoliwei
 * @date 2019/11/25 14:49
 */
public class User {

    private Integer id;

    private String userName;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User { id= ").append(id).append(". userName=").append(userName).append(", desc=").append(desc);
        return sb.toString();
    }
}
