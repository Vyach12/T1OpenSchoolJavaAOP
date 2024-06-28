package openschool.java.aop.executiontime.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record ClassStatisticsTO(
        String className,
        Set<MethodStatisticsTO> methods
) {
}
