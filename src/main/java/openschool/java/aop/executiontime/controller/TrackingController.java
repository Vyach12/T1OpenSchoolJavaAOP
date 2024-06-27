package openschool.java.aop.executiontime.controller;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.repository.ExecutionTimeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final ExecutionTimeRepository repository;

    @GetMapping("/statistics")
    public List<ExecutionTimeEntity> statistics() {
        return repository.findAll();
    }

    @GetMapping
    public Map<String, Double> getAverageTime() {
        List<ExecutionTimeEntity> times = repository.findAll();

        return times.stream()
                .collect(Collectors.groupingBy(ExecutionTimeEntity::getMethodName,
                        Collectors.averagingLong(ExecutionTimeEntity::getDuration)));
    }
}
