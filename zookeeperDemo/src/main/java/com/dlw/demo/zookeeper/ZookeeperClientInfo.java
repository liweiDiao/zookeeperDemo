package com.dlw.demo.zookeeper;

/**
 * @Description zk客户端的信息类
 * @Author diaoliwei
 * @Date 2018/10/23 17:32
 */
public class ZookeeperClientInfo {

    /**
     * 是否是leader 默认为false
     */
    public static boolean isLeader = false;

    /**
     * 客户端ID
     */
    private String id;

    /**
     * 连接信息字符串
     */
    private String connectString;

    /**
     * 节点路径
     */
    private String path;

    /**
     * 连接超时时间
     */
    private Integer connectTimeOut;

    /**
     * 最大连接次数
     */
    private Integer maxRetries;

    /**
     * 重连休眠时间
     */
    private Integer retrySleepTime;

    public static boolean isLeader() {
        return isLeader;
    }

    public static void setIsLeader(boolean isLeader) {
        ZookeeperClientInfo.isLeader = isLeader;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConnectString() {
        return connectString == null ? null : connectString.replaceAll("//s+", "");
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(Integer connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ZookeeperClientInfo{ ").append("id=").append(id)
                .append(",isLeader=").append(isLeader).append(", connectString=").append(connectString)
                .append(", path=").append(path).append(",connectTimeOut=").append(connectTimeOut)
                .append(", maxRetries=").append(maxRetries).append(", retrySleepTime=").append(retrySleepTime).append(" }");
        return sb.toString();
    }
}
