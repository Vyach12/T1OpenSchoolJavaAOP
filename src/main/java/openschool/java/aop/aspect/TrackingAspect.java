package openschool.java.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import openschool.java.aop.annotation.TrackTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TrackingAspect {

    @Around("@annotation(openschool.java.aop.annotation.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        log.info("Time elapsed: {} ms", endTime - startTime);

        return result;
    }
}
