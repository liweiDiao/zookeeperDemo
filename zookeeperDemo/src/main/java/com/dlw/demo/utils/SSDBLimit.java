package com.dlw.demo.utils;

import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author diaoliwei
 * @Date 2021/1/20 17:15
 */
public class SSDBLimit {

    /**
     *  限流方法（滑动时间算法）
     * @param key   限流标识
     * @param period  限流时间范围（单位：秒）
     * @param maxCount 最大运行访问次数
     * @return
     */
    public static boolean isPeriodLimiting(SSDB ssdb, String key, int period, int maxCount) {
        long nowTs = System.currentTimeMillis(); // 当前时间戳

        // 删除非时间段内的请求数据（清除老访问数据，比如 period=60 时，标识清除 60s 以前的请求记录）
        ssdb.zremrangebyscore(key, 0, nowTs - period * 1000);
        Response currCount = ssdb.zcount(key, 0 , 999999999); // 当前请求次数
        if (currCount.asInt() >= maxCount) {
            // 超过最大请求次数，执行限流
            return false;
        }
        // 未达到最大请求数，正常执行业务
        ssdb.zset(key, "" + nowTs, nowTs); // 请求记录 +1
        return true;
    }

}
