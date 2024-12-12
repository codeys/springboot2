package com.springboot2.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0
 * @title TaskSchedulingService
 * @description quartz定时器工具类
 * @create 2024/11/8 9:54
 */
@Slf4j
public class TaskSchedulingUtil {
    private static TaskSchedulingUtil taskSchedulingService = null;
    private Scheduler scheduler = null;


    public TaskSchedulingUtil(DataSourceProperties dataSourceProperties) {
        createScheduler(dataSourceProperties);
    }


    private void createScheduler(DataSourceProperties dataSourceProperties) {
        try {

            StdSchedulerFactory factory = new StdSchedulerFactory( getQuartzProperties(dataSourceProperties));
            scheduler = factory.getScheduler();
        } catch (Exception e) {
        }
    }


    /**
     * 单例获取任务调度器
     */
//    public static synchronized TaskSchedulingService getInstance() {
//        if (taskSchedulingService == null || taskSchedulingService.scheduler == null) {
//            return new TaskSchedulingService();
//        }
//        return taskSchedulingService;
//    }

    /**
     * 添加定时任务
     * @param taskName
     * @param groupName
     * @param className
     */
    public void addTask(String taskName, String groupName,Class className,String cornExpression) {
        JobDetail jobDetail = JobBuilder.newJob(className)
                .usingJobData("name","yu")
                .withIdentity(taskName,groupName)
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                /**给当前JobDetail添加参数，K V形式，链式调用，可以传入多个参数，在Job实现类中，可以通过jobExecutionContext.getTrigger().getJobDataMap().get("orderNo")获取值*/
                .usingJobData("orderNo", "123456")
                /**添加认证信息，有3种重写的方法，我这里是其中一种，可以查看源码看其余2种*/
                .withIdentity(taskName,groupName)
                /**开始执行时间*/
                .startNow()
                /**添加执行规则，SimpleTrigger、CronTrigger的区别主要就在这里,我这里是demo，写了个每5秒钟执行一次*/
                .withSchedule(CronScheduleBuilder.cronSchedule(cornExpression))
                .build();//执行

        try {
            JobKey jobKey = JobKey.jobKey(taskName, groupName);
            boolean isExist = scheduler.checkExists(jobKey);
            if (!isExist) {
                scheduler.scheduleJob(jobDetail,trigger);
            }
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停定时任务
     * @param taskName
     * @param groupName
     */
    public void pauseTask(String taskName, String groupName) {
//        JobKey jobKey = JobKey.jobKey(taskName, groupName);
        TriggerKey triggerKey = TriggerKey.triggerKey(taskName,groupName);
        boolean isExist = false;
        try {
            isExist = scheduler.checkExists(triggerKey);
            if (isExist) {
                scheduler.pauseTrigger(triggerKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复定时任务
     * @param taskName
     * @param groupName
     */
    public void resumeTask(String taskName, String groupName) {
//        JobKey jobKey = JobKey.jobKey(taskName, groupName);
        TriggerKey triggerKey = TriggerKey.triggerKey(taskName,groupName);
        boolean isExist = false;
        try {
            isExist = scheduler.checkExists(triggerKey);
            if (isExist) {
                scheduler.resumeTrigger(triggerKey);
                if (!scheduler.isStarted()) {
                    scheduler.start();
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除定时任务
     * @param taskName
     * @param groupName
     */
    public void deleteTask(String taskName, String groupName) {
        JobKey jobKey = JobKey.jobKey(taskName, groupName);
        boolean isExist = false;
        try {
            isExist = scheduler.checkExists(jobKey);
            if (isExist) {
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有定时任务
     * @param jobGrop
     */
    public void getAllTask(String jobGrop) {
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            if (null != jobKeys && !jobKeys.isEmpty()) {
                Iterator<JobKey> it = jobKeys.iterator();
                while (it.hasNext()) {
                    JobKey jobKey = it.next();
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    if (jobGroup != null && jobGroup.equals(jobGrop)) {
                        if (jobName != null && !jobName.equals("")) {
                        }
                    }
                }
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Properties getQuartzProperties(DataSourceProperties dataSourceProperties) {
        Properties properties = new Properties();
        String quartzPropertiesFilaName = "quartz.properties";
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(quartzPropertiesFilaName)) {
            properties.load(inputStream);
            properties.setProperty("org.quartz.dataSource.myDS.driver", dataSourceProperties.getDriverClassName());
            properties.setProperty("org.quartz.dataSource.myDS.URL", dataSourceProperties.getUrl());
            properties.setProperty("org.quartz.dataSource.myDS.user", dataSourceProperties.getUsername());
            properties.setProperty("org.quartz.dataSource.myDS.password", dataSourceProperties.getPassword());
            properties.setProperty("org.quartz.dataSource.myDS.maxConnection", "30");
        } catch (Exception e) {

        }
        return properties;
    }

    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(Level.INFO);
        });

    }

//    public static void main(String[] args) {
//        TaskSchedulingService.getInstance().addTask("test","testGroup", MyJob.class,"*/5 * * * * ?");
//    }

}
