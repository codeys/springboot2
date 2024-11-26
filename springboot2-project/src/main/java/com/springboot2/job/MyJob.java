package com.springboot2.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Administrator
 * @version 1.0
 * @title Myjob
 * @description
 * @create 2024/11/8 9:59
 */
@Slf4j
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
      log.info("quart定时器执行中。。。。。。");
    }
}
