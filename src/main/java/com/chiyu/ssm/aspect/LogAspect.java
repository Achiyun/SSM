package com.chiyu.ssm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect // 声明当前类为切面类
public class LogAspect {

    @Around("execution(* com.chiyu.ssm.service..*.*(..))")
    public Object printLogInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.debug("当前执行方法的类： {}", joinPoint.getTarget().getClass());
        log.debug("当前方法 {} , 参数 {} ", methodName, Arrays.toString(joinPoint.getArgs()));
        Object res = joinPoint.proceed();
        log.debug("--------方法: {} 执行完成", methodName);
        return res;
    }
}
