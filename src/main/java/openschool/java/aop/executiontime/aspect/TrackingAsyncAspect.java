package openschool.java.aop.executiontime.aspect;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.service.ExecutionTimeCreateUseCase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackingAsyncAspect {

    private final ExecutionTimeCreateUseCase executionTimeCreateUseCase;

    @Around("@annotation(openschool.java.aop.executiontime.aspect.annotation.TrackAsyncTime)")
    public Object trackAsyncTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        executionTimeCreateUseCase.save(ExecutionTimeEntity.builder()
                .className(joinPoint.getSignature().getDeclaringTypeName())
                .methodName(joinPoint.getSignature().getName())
                .durationMs(duration)
                .build()
        );

        return result;
    }
}
