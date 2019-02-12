package com.dlw.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping("/index")
    public String index(){
        log.error("HelloController=====error====");
        log.warn("HelloController=====warn====");
        log.info("HelloController=====info====");
        return "hello world";
    }

}
