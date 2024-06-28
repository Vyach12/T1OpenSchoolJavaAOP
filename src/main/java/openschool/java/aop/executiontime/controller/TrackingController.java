package openschool.java.aop.executiontime.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.dto.ClassStatisticsTO;
import openschool.java.aop.executiontime.dto.ExecutionTimeStatisticsTO;
import openschool.java.aop.executiontime.service.ExecutionTimeFindUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/v1/tracking")
@RequiredArgsConstructor
@Tag(name = "TrackingController", description = "Контроллер, предоставляющий стастику по времени выполнения методов ")
public class TrackingController {

    private final ExecutionTimeFindUseCase findUseCase;

    @GetMapping("/all")
    @Operation(summary = "Возвращает все измерения времени")
    public List<ExecutionTimeEntity> getAllMeasurements() {
        return findUseCase.getAllMeasurements();
    }

    //Сделать отдельную TO
    @GetMapping
    @Operation(summary = "Возвращает всю информацию о методах для всех классов")
    public Set<ClassStatisticsTO> getAllClassInfo() {
        return findUseCase.getClassInfo();
    }

    @GetMapping("/{className}")
    @Operation(summary = "Возвращает информацию о классе с именем {className}")
    public Set<ClassStatisticsTO> getClassInfo(@PathVariable String className) {
        return findUseCase.getClassInfo(className);
    }
}
