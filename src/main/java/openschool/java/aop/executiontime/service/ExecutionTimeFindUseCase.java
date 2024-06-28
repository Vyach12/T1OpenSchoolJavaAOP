package openschool.java.aop.executiontime.service;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.dto.MethodInfoTO;
import openschool.java.aop.executiontime.repository.ExecutionTimeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public Map<String, Map<String, Double>> getAverageTime() {
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

    public Map<String, Map<String, Long>> getTotalTime() {
        List<ExecutionTimeEntity> times = repository.findAll();

        return times.stream()
                .collect(Collectors.groupingBy(
                        ExecutionTimeEntity::getClassName,
                        Collectors.groupingBy(
                                ExecutionTimeEntity::getMethodName,
                                Collectors.summingLong(ExecutionTimeEntity::getDuration)
                        )
                ));
    }

    public Map<String, Map<String, MethodInfoTO>> getClassInfo() {
        List<ExecutionTimeEntity> executionTimes = repository.findAll();

        Map<String, Map<String, MethodInfoTO>> result = new HashMap<>();

        for (var executionTime : executionTimes) {
            int numberOfCalls = 1;
            long totalTime = executionTime.getDuration();
            double averageTime = totalTime;

            if (result.containsKey(executionTime.getClassName())) {
                var methodsMap = result.get(executionTime.getClassName());

                if (methodsMap.containsKey(executionTime.getMethodName())) {
                    MethodInfoTO infoTO = methodsMap.get(executionTime.getMethodName());

                    numberOfCalls += infoTO.getNumberOfCalls();
                    totalTime += infoTO.getTotalTime();
                    averageTime = (double) totalTime / numberOfCalls;

                    infoTO.setAverageTime(averageTime);
                    infoTO.setTotalTime(totalTime);
                    infoTO.setNumberOfCalls(numberOfCalls);
                } else {
                    methodsMap.put(executionTime.getMethodName(),
                            MethodInfoTO.builder()
                                    .numberOfCalls(numberOfCalls)
                                    .averageTime(averageTime)
                                    .totalTime(totalTime)
                                    .build());
                }
            } else {
                Map<String, MethodInfoTO> methodsMap = new HashMap<>();
                methodsMap.put(executionTime.getMethodName(),
                        MethodInfoTO.builder()
                                .numberOfCalls(numberOfCalls)
                                .averageTime(averageTime)
                                .totalTime(totalTime)
                                .build());
                result.put(executionTime.getClassName(), methodsMap);
            }
        }

        return result;
    }

    public Map<String, Map<String, MethodInfoTO>> getClassInfo(String className) {
        List<ExecutionTimeEntity> executionTimes = repository.findAllByClassName(className);

        Map<String, Map<String, MethodInfoTO>> result = new HashMap<>();

        for (var executionTime : executionTimes) {
            int numberOfCalls = 1;
            long totalTime = executionTime.getDuration();
            double averageTime = totalTime;

            if (result.containsKey(executionTime.getClassName())) {
                var methodsMap = result.get(executionTime.getClassName());

                if (methodsMap.containsKey(executionTime.getMethodName())) {
                    MethodInfoTO infoTO = methodsMap.get(executionTime.getMethodName());

                    numberOfCalls += infoTO.getNumberOfCalls();
                    totalTime += infoTO.getTotalTime();
                    averageTime = (double) totalTime / numberOfCalls;

                    infoTO.setAverageTime(averageTime);
                    infoTO.setTotalTime(totalTime);
                    infoTO.setNumberOfCalls(numberOfCalls);
                } else {
                    methodsMap.put(executionTime.getMethodName(),
                            MethodInfoTO.builder()
                                    .numberOfCalls(numberOfCalls)
                                    .averageTime(averageTime)
                                    .totalTime(totalTime)
                                    .build());
                }
            } else {
                Map<String, MethodInfoTO> methodsMap = new HashMap<>();
                methodsMap.put(executionTime.getMethodName(),
                        MethodInfoTO.builder()
                                .numberOfCalls(numberOfCalls)
                                .averageTime(averageTime)
                                .totalTime(totalTime)
                                .build());
                result.put(executionTime.getClassName(), methodsMap);
            }
        }

        return result;
    }
}
