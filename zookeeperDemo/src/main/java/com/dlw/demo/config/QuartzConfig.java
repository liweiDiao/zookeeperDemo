package com.dlw.demo.config;

import com.dlw.demo.quartz.TestTask;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 定时任务相关配置
 * @Author diaoliwei
 * @Date 2018/11/19 14:28
 */
@Configuration
public class QuartzConfig {

    /**
     * 定时任务的 corn
     */
    @Value("${spring.quartz.corn.testTask}")
    private String testTask;

    /**
     * 定义Job的实例
     * @return
     */
    @Bean
    public JobDetail testJobDetaiBuilder() {
        return JobBuilder.newJob(TestTask.class).withIdentity("TestTask", "group1").storeDurably().build(); //任务执行类、任务名、任务组
    }

    /**
     * 触发Job的执行, 创建Trigger
     * @return
     */
    @Bean
    public Trigger testTriggerBuilder() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(testTask);
        return TriggerBuilder.newTrigger().forJob(testJobDetaiBuilder()).withIdentity("TestTrigger", "group1").withSchedule(scheduleBuilder).build();
    }
}
