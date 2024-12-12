package com.springboot2.config;

import com.springboot2.util.TaskSchedulingUtil;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @version 1.0
 * @title GlobalConfig
 * @description
 * @create 2024/11/8 11:17
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class GlobalConfig {

    /**
     * quartz调度器
     * @param dataSourceProperties
     * @return
     */
    @Bean
    public TaskSchedulingUtil taskSchedulingService(DataSourceProperties dataSourceProperties) {
        return new TaskSchedulingUtil(dataSourceProperties);
    }
}
