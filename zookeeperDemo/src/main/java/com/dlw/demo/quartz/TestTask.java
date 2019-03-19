package com.dlw.demo.quartz;

import com.dlw.demo.utils.JodaDateUtil;
import com.dlw.demo.zookeeper.ZookeeperClientInfo;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @Description 定时任务测试
 * @Author diaoliwei
 * @Date 2018/11/19 13:59
 */
public class TestTask extends QuartzJobBean {

    private final static Logger log = LoggerFactory.getLogger(TestTask.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("======>>>> TestTask start quartz......{}", JodaDateUtil.date2String(new Date()));

        if (!ZookeeperClientInfo.isLeader) {
            log.warn("======TestTask>>>>>>>>当前服务不是leader，不执行任务======>>>>>>>>");
            return;
        }

        log.info("======>>>> TestTask end quartz......{}", JodaDateUtil.date2String(new Date()));
    }
}
