package com.dlw.demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;

/**
 * @Description zk的客户端类
 * @Author diaoliwei
 * @Date 2018/10/23 17:25
 */
public class ZookeeperClient {

    /**
     * 客户端
     */
    private CuratorFramework client;

    /**
     * Leader选举
     */
    private LeaderLatch leader;

    public ZookeeperClient(LeaderLatch leader,CuratorFramework client){
        this.client = client;
        this.leader = leader;
    }

    /**
     * 启动客户端
     * @throws Exception
     */
    public void startZKClient() throws Exception {
        client.start();
        leader.start();
    }

    /**
     * 关闭客户端
     * @throws Exception
     */
    public void closeZKClient() throws Exception {
        leader.close();
        client.close();
    }

    /**
     * 判断是否变为领导者
     * @return
     */
    public boolean hasLeadership(){
        return leader.hasLeadership();
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public LeaderLatch getLeader() {
        return leader;
    }

    public void setLeader(LeaderLatch leader) {
        this.leader = leader;
    }
}
