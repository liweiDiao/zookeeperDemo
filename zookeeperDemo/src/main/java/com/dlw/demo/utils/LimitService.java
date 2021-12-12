package com.dlw.demo.utils;

import org.joda.time.DateTime;

import java.util.concurrent.locks.Lock;

/**
 * @Description
 * @Author diaoliwei
 * @Date 2021/1/20 17:03
 */
public class LimitService {

    //当前指针的位置
    int currentIndex = 0;

    //限制的时间的秒数，即：x秒允许多少请求
    int limitTimeSencond = 1;

    //请求环的容器数组
    DateTime[] requestRing = null;

    //容器改变或者移动指针时候的锁
    Object objLock = new Object();

    public LimitService(int countPerSecond, int limitTimeSencond) {
        requestRing = new DateTime[countPerSecond];
        this.limitTimeSencond = limitTimeSencond;
    }

    /**
     * 程序是否可以继续
     * @return
     */
    public boolean isContinue() {
        DateTime currentNode = requestRing[currentIndex];
//        if (currentNode != null && currentNode)

        return false;
    }
}
