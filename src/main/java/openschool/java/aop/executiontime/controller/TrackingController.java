package openschool.java.aop.executiontime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.service.ExecutionTimeFindUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/tracking")
@RequiredArgsConstructor
@Tag(name = "TrackingController", description = "Контроллер, предоставляющий стастику по времени выполнения методов ")
public class TrackingController {

    private final ExecutionTimeFindUseCase findUseCase;

    @GetMapping("/all")
    @Operation(summary = "Возвращает все измерения времени")
    public List<ExecutionTimeEntity> getAll() {
        return findUseCase.getAll();
    }

    @GetMapping("/average")
    @Operation(summary = "Возвращает среднее время выполнения методов")
    public Map<String, Double> getAverageTime() {
        return findUseCase.getAverageTime();
    }

    @GetMapping("/total")
    @Operation(summary = "Возвращает среднее время выполнения методов")
    public Map<String, Long> getTotalTime() {
        return findUseCase.getTotalTime();
    }

    @GetMapping("/grouping")
    public Map<String, Map<String, Double>> getExecutionTimesStatisticsByClass() {
        return findUseCase.getExecutionTimesStatisticsByClass();
    }
}
