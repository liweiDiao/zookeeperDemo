package com.dlw.demo.zookeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description zk相关配置
 * @Author diaoliwei
 * @Date 2018/10/23 18:01
 */
@Component
public class ZookeeperConfig {

    /**
     * zk 地址
     */
    @Value("${spring.slaveof.zk.addr}")
    private String addr;

    /**
     * 重试策略----最大重试次数
     */
    @Value("${spring.slaveof.zk.max}")
    private int max;

    /**
     * 重试策略-----sleepTime
     */
    @Value("${spring.slaveof.zk.sleep}")
    private int sleepTime;

    /**
     * 连接超时时间
     */
    @Value("${spring.slaveof.zk.connection}")
    private int connectionTime;

    /**
     * 会话超时时间
     */
    @Value("${spring.slaveof.zk.session}")
    private int sessionTime;

    public String getAddr() {
        return addr;
    }

    public int getMax() {
        return max;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public int getSessionTime() {
        return sessionTime;
    }
}
