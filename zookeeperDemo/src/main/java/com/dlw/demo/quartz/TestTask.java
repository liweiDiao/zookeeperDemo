package com.dlw.demo.quartz;

import com.dlw.demo.constant.Constant;
import com.dlw.demo.utils.JodaDateUtil;
import com.dlw.demo.zookeeper.ZookeeperClientInfo;
import org.nutz.ssdb4j.spi.SSDB;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @Description 定时任务测试
 * @Author diaoliwei
 * @Date 2018/11/19 13:59
 */
public class TestTask extends QuartzJobBean {

    private final static Logger log = LoggerFactory.getLogger(TestTask.class);

    @Autowired
    private SSDB ssdb;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("======>>>> TestTask start quartz......{}", JodaDateUtil.date2String(new Date()));

        if (!ZookeeperClientInfo.isLeader) {
            log.warn("======TestTask>>>>>>>>当前服务不是leader，不执行任务======>>>>>>>>");
            return;
        }

        /**
         * TODO 具体业务代码处理， 可以用ssdb (redis也可以)存储该定时任务是否正常执行完成。
         * try catch 异常时在ssdb中存储一个标识。当前机器宕机后，切换到另外一个机器从ssdb 中查询是否执行完成，
         * 未完成继续执行该定时任务。
         */
        ssdb.set(Constant.TEST_STATE, Constant.TASK_EXECUTING);
        try {
            // service.do(); 伪代码
            log.info("service.do();");
            ssdb.set(Constant.TEST_STATE, Constant.TASK_END);
        } catch (Exception e) {
            ssdb.set(Constant.TEST_STATE, Constant.TASK_EXECUTING); // 标识当前定时任务未正常执行完成。
            log.error("=====TestTask=====error:{}", e);
        }
        log.info("======>>>> TestTask end quartz......{}", JodaDateUtil.date2String(new Date()));
    }
}
