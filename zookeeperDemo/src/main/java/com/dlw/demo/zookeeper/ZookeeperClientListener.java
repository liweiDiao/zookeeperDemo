package com.dlw.demo.zookeeper;

import com.dlw.demo.utils.JodaDateUtil;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Description zk监听主从变更
 * @Author diaoliwei
 * @Date 2018/10/23 17:40
 */
public class ZookeeperClientListener implements LeaderLatchListener {

    private final static Logger log = LoggerFactory.getLogger(ZookeeperClientListener.class);

    @Override
    public void isLeader() {
        log.error(JodaDateUtil.date2String(new Date()) + ",当前服务已变为leader,将从事业务消费======>>>>");
        ZookeeperClientInfo.isLeader = true;
    }

    @Override
    public void notLeader() {
        log.error(JodaDateUtil.date2String(new Date()) + ",当前服务已退出leader,不再从事消费业务=====>>>");
        ZookeeperClientInfo.isLeader = false;
    }
}
