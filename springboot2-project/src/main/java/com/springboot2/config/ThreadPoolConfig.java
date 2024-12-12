package com.springboot2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * @ClassName ThreadPoolConfig
 * @Description 线程池
 * @Author shuai_yu
 * @Date 2021/8/5 10:38
 **/
@Configuration
@PropertySource("classpath:/properties/threadPool.properties")
@ConfigurationProperties(prefix = "thread.pool")
@Data
public class ThreadPoolConfig {
    // 核心线程池大小
    private int corePoolSize;

    // 最大可创建的线程数
    private int maxPoolSize;

    // 队列最大长度
    private int queueCapacity;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadFactory(new CustomThreadFactory());
        // 线程池对拒绝任务(无线程可用)的处理策略
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.setRejectedExecutionHandler(new CustomRejectedExecutionHandler());
        return executor;
    }

    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        private int errorNum = 0;
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            errorNum++;
            System.out.println("error....." + errorNum);
        }
    }

    private class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("testPool" + "-->" + atomicInteger.addAndGet(1));
            return thread;
        }
    }

}
