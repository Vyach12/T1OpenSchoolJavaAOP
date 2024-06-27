package openschool.java.aop.executiontime.service;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.repository.ExecutionTimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExecutionTimeFindUseCase {

    private final ExecutionTimeRepository repository;

    public List<ExecutionTimeEntity> getAll() {
        return repository.findAll();
    }

    public Map<String, Double> getAverageTime() {
        List<ExecutionTimeEntity> times = repository.findAll();

        return times.stream()
                .collect(Collectors.groupingBy(ExecutionTimeEntity::getMethodName,
                        Collectors.averagingLong(ExecutionTimeEntity::getDuration)));
    }

    public Map<String, Long> getTotalTime() {
        List<ExecutionTimeEntity> times = repository.findAll();

        return times.stream()
                .collect(Collectors.groupingBy(ExecutionTimeEntity::getMethodName,
                        Collectors.summingLong(ExecutionTimeEntity::getDuration)));
    }

    public Map<String, Map<String, Double>> getExecutionTimesStatisticsByClass() {
        List<ExecutionTimeEntity> times = repository.findAll();

        return times.stream()
                .collect(Collectors.groupingBy(
                        ExecutionTimeEntity::getClassName,
                        Collectors.groupingBy(
                                ExecutionTimeEntity::getMethodName,
                                Collectors.averagingLong(ExecutionTimeEntity::getDuration)
                        )
                ));
    }
}
