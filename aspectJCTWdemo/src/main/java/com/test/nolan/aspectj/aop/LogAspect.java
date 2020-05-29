package com.test.nolan.aspectj.aop;

import com.test.nolan.aspectj.annotation.LogAnnotation;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * description:
 *
 * @author zhunhuang, 2020/4/19
 */
@Aspect
public class LogAspect {

    @Pointcut("@annotation(logAnnotation)")
    public void callAt(LogAnnotation logAnnotation) {
    }

    @Around("callAt(logAnnotation)")
    public Object doAround(ProceedingJoinPoint pjp, LogAnnotation logAnnotation) throws Throwable {
        Object[] args = pjp.getArgs();
        System.out.println("请求方法" + pjp.getSignature().getName() + "请求参数:" + args);
        return pjp.proceed();
    }

    public <T> T aspectOf(Class<T> tClass){
        return Aspects.aspectOf(tClass);
    }
}
