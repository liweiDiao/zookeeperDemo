package com.dlw.demo.controller;

import com.dlw.demo.utils.SSDBLimit;
import org.nutz.ssdb4j.spi.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试
 * @Author diaoliwei
 * @Date 2018/10/17 10:36
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    private final static Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private SSDB ssdb;

    @RequestMapping("/index")
    public String index(){
        log.error("HelloController=====error====");
        log.warn("HelloController=====warn====");
        log.info("HelloController=====info====");

        try {
            ssdb.set("test", 1111);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello world";
    }
    
    @RequestMapping("/index2")
    public String index2(){
        for (int i = 0; i < 15; i ++) {
            boolean res = SSDBLimit.isPeriodLimiting(ssdb,"index", 3, 10);
            if (res) {
                log.error("正常执行请求：" + i);
            } else {
                log.error("被限流：" + i);
            }
        }

        try {
            Thread.sleep(4000);
            // 超过最大执行时间之后，再从发起请求
            boolean res = SSDBLimit.isPeriodLimiting(ssdb, "index", 3, 10);
            if (res) {
                log.error("休眠后，正常执行请求");
            } else {
                log.error("休眠后，被限流");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.error("HelloController=====error====");
        log.warn("HelloController=====warn====");
        log.info("HelloController=====info====");

        try {
            ssdb.set("test", 1111);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello world";
    }

}
