package com.dlw.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description chatgpt test
 * @Author diaoliwei
 * @Date 2023/4/3 14:33
 */
@RestController
@RequestMapping("/chat")
public class ChatGPTController {

    @Value("${chatgpt.url}")
    private String url;

    @Value("${chatgpt.port}")
    private Integer port;

    @Value("${chatgpt.apiUrl}")
    private String apiUrl;

    @Value("${chatgpt.apiKey}")
    private String apiKey;

   /* public R<ChatGptRet> chatGPTproxy(@RequestBody ChatGPTRequest chatGPTRequest){

    }*/


}
