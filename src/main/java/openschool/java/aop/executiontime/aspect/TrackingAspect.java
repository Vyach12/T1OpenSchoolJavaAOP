package openschool.java.aop.executiontime.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.service.ExecutionTimeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TrackingAspect {

    private final ExecutionTimeService executionTimeService;

    @Around("@annotation(openschool.java.aop.executiontime.aspect.annotation.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        log.info("Time elapsed: {} ms", duration);
        executionTimeService.save(ExecutionTimeEntity.builder()
                .methodName(joinPoint.getSignature().toLongString())
                .duration(duration)
                .build()
        );

        return result;
    }
}
