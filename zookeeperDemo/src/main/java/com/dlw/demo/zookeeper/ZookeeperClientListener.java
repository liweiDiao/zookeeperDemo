package com.dlw.demo.zookeeper;

import com.dlw.demo.service.ChangeLeaderService;
import com.dlw.demo.utils.JodaDateUtil;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description zk监听主从变更
 * @Author diaoliwei
 * @Date 2018/10/23 17:40
 */
@Component
public class ZookeeperClientListener implements LeaderLatchListener {

    private final static Logger log = LoggerFactory.getLogger(ZookeeperClientListener.class);

    @Autowired
    private ChangeLeaderService changeLeaderService;

    /**
     *  将本服务达成jar包，部署到2台服务器上。启动两个服务。
     *  1、第一台服务（机器1）启动后抢到leader，会进入到该方法中。另外一台服务（机器2）会进入到notLeader()中。
     *  2、当机器1宕机后，连接断开后zookeeper会删除临时节点。机器2根据选举会成为leader，成为leader后会进入到isLeader()中
     *     然后在changeLeaderService.taskExecut() 再次将定时任务做补偿处理。
     */
    @Override
    public void isLeader() {
        log.error("{},当前服务已变为leader,将从事业务消费======>>>>", JodaDateUtil.date2String(new Date()));
        ZookeeperClientInfo.isLeader = true;

        // 切换机器后，继续执行上一个机器未完成的定时任务。
        changeLeaderService.taskExecut();
    }

    @Override
    public void notLeader() {
        log.error("{},当前服务已退出leader,不再从事消费业务=====>>>", JodaDateUtil.date2String(new Date()));
        ZookeeperClientInfo.isLeader = false;
    }
}
