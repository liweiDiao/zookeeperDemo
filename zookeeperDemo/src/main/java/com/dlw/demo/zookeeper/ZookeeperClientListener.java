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

    @Override
    public void isLeader() {
        log.error(JodaDateUtil.date2String(new Date()) + ",当前服务已变为leader,将从事业务消费======>>>>");
        ZookeeperClientInfo.isLeader = true;

        // 切换机器后，继续执行上一个机器未完成的定时任务。
        changeLeaderService.taskExecut();
    }

    @Override
    public void notLeader() {
        log.error(JodaDateUtil.date2String(new Date()) + ",当前服务已退出leader,不再从事消费业务=====>>>");
        ZookeeperClientInfo.isLeader = false;
    }
}
