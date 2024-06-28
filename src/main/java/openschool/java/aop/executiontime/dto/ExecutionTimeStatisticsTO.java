package openschool.java.aop.executiontime.dto;

import lombok.Builder;

@Builder
public record ExecutionTimeStatisticsTO(
        int numberOfCalls,
        long totalTime,
        double averageTime
) {
}
