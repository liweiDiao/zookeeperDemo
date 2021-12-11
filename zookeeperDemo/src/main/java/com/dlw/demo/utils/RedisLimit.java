package com.dlw.demo.utils;

import redis.clients.jedis.Jedis;

/**
 * @Description
 * @Author diaoliwei
 * @Date 2021/1/20 17:37
 */
public class RedisLimit {

    // Redis 操作客户端
    static Jedis jedis = new Jedis("192.168.223.11", 6300);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            boolean res = isPeriodLimiting("javaTest", 3, 10);
            if (res) {
                System.out.println("正常执行请求：" + i);
            } else {
                System.out.println("被限流：" + i);
            }
        }
        // 休眠 4s
        Thread.sleep(4000);
        // 超过最大执行时间之后，再从发起请求
        boolean res = isPeriodLimiting("javaTest", 3, 10);
        if (res) {
            System.out.println("休眠后，正常执行请求");
        } else {
            System.out.println("休眠后，被限流");
        }
    }

    /**
     * 限流方法（滑动时间算法）
     * @param key      限流标识
     * @param period   限流时间范围（单位：秒）
     * @param maxCount 最大运行访问次数
     * @return
     */
    private static boolean isPeriodLimiting(String key, int period, int maxCount) {
        long nowTs = System.currentTimeMillis(); // 当前时间戳
        // 删除非时间段内的请求数据（清除老访问数据，比如 period=60 时，标识清除 60s 以前的请求记录）
        System.out.println("====" + (nowTs - period * 1000));
        jedis.zremrangeByScore(key, 0, nowTs - period * 1000);
        long currCount = jedis.zcard(key); // 当前请求次数
        System.out.println("==isPeriodLimiting()=====currCount:"+currCount);
        if (currCount >= maxCount) {
            // 超过最大请求次数，执行限流
            return false;
        }
        // 未达到最大请求数，正常执行业务
        jedis.zadd(key, nowTs, "" + nowTs); // 请求记录 +1
        return true;
    }

}
