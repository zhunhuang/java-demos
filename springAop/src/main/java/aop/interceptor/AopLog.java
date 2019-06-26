/*
 * @project spring4-stepbystep
 * @package com.stepbystep.spring4.samples.aop.aspect
 * @file AopLog.java
 * @version  1.0
 * @author  junming.wang
 * @time 2016-05-19
 */
package aop.interceptor;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <code>AopLog</code>
 *
 * @author junming.wang
 * @version 1.0
 * @since 1.0 2016-05-19
 */
@Aspect
@Component
public class AopLog {
    private static final Logger LOG = LoggerFactory
            .getLogger(AopLog.class);

    /**
     * 语法:  返回值类型 包名.类名.方法名(参数类型)
     */
    @Pointcut("execution( * service.*.*(..))")
    public void aspect(){}

    @Pointcut("execution(public * service.HelloService.*(..))")
    public void aspect2(){}

    @Around("aspect()")
    public Object around(JoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            LOG.info("=======before {}",joinPoint.getSignature().getName());
            LOG.info("=======请求参数 {}",joinPoint.getArgs());
            Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
            LOG.info("=======返回参数 {}",proceed);
            LOG.info("=======after {}",joinPoint.getSignature().getName());
            long end = System.currentTimeMillis();
            LOG.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            return proceed;
        } catch (Throwable e) {
            LOG.error(e.getMessage(),e);
            long end = System.currentTimeMillis();
            LOG.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            throw e;
        }
    }
}
