package openschool.java.aop.executiontime.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.service.ExecutionTimeCreateUseCase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TrackingAsyncAspect {

    private final ExecutionTimeCreateUseCase executionTimeCreateUseCase;

    @Around("@annotation(openschool.java.aop.executiontime.aspect.annotation.TrackAsyncTime)")
    public Object trackAsyncTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        log.info("Time elapsed: {} ms", duration);
        executionTimeCreateUseCase.save(ExecutionTimeEntity.builder()
                .className(joinPoint.getSignature().getDeclaringTypeName())
                .methodName(joinPoint.getSignature().getName())
                .duration(duration)
                .build()
        );

        return result;
    }
}
