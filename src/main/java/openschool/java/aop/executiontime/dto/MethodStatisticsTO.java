package openschool.java.aop.executiontime.dto;

import lombok.Builder;

@Builder
public record MethodStatisticsTO(
        String methodName,
        ExecutionTimeStatisticsTO statisticInfoTO
) {
}
