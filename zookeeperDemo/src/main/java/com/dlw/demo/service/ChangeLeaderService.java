package com.dlw.demo.service;

/**
 * @Description 主从服务切换时
 * @Author diaoliwei
 * @Date 2019/6/10 9:40
 */
public interface ChangeLeaderService {

    /**
     * 主从服务切换时，手动触发分配到leader机器上的定时任务中的业务逻辑
     */
    void taskExecut();
}
