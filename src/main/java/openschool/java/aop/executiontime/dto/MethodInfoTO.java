package openschool.java.aop.executiontime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MethodInfoTO {
    private int numberOfCalls;
    private long totalTime;
    private double averageTime;
}
