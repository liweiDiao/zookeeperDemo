package com.dlw.demo.service.impl;

import com.dlw.demo.service.ChangeLeaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description 服务主从切换触发业务代码
 * @Author diaoliwei
 * @Date 2019/6/10 9:41
 */
@Service
public class ChangeLeaderServiceImpl implements ChangeLeaderService {

    private final static Logger log = LoggerFactory.getLogger(ChangeLeaderServiceImpl.class);

    @Override
    public void taskExecut() {
        // 从redis 中查询出定时任务的标识，是否正常执行完成，未完成的话，在这里再触发执行。

    }
}
