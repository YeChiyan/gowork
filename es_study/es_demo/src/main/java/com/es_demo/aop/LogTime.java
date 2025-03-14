package com.contractdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
@Slf4j
public class LogTime {
        @Around("@annotation(com.contractdemo.anno.LogTimeAnno)")
        public void calculateTime(ProceedingJoinPoint joinPoint){
            long start = System.currentTimeMillis();
            try {
                joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            long end=System.currentTimeMillis();
            long use = end - start;
            log.info(joinPoint.getSignature().getName()+":执行时间是"+use+"ms");

        }
}
