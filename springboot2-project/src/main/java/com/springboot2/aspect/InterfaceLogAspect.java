package com.springboot2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Administrator
 * @version 1.0
 * @title InterfaceLogAspect
 * @description
 * @create 2024/11/26 13:54
 */
@Slf4j
@Component
@Aspect
public class InterfaceLogAspect {

    // 定义切点
    // 这里使用注解的方式来定义切点，可以根据需要自行修改
    // 例如：@Pointcut("@annotation(com.springboot2.annotation.InterfaceLog)")
    // 或者使用正则表达式来定义切点
    // 例如：@Pointcut("execution(* com.springboot2.service.*.*(..))")
    // 或者使用表达式来定义切点
    // 例如：@Pointcut("com.springboot2.service..*.*(..)")
    @Before("execution(* com.springboot2.controller..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes!= null) {
            HttpServletRequest request = attributes.getRequest();
            log.info("请求URL: {}", request.getRequestURL().toString());
            log.info("请求方法: {}", request.getMethod());
            log.info("请求IP: {}", request.getRemoteAddr());
            log.info("请求类方法: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            log.info("请求参数: {}", Arrays.toString(joinPoint.getArgs()));
        }
    }
    @AfterReturning(pointcut = "execution(* com.springboot2.controller..*.*(..))", returning = "result")
    public void logAfter(Object result) {
        log.info("返回结果: {}", result);
    }
}
