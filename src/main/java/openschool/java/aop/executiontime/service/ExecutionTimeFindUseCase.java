package openschool.java.aop.executiontime.service;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.dto.MethodInfoTO;
import openschool.java.aop.executiontime.repository.ExecutionTimeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExecutionTimeFindUseCase {

    private final ExecutionTimeRepository repository;

    public List<ExecutionTimeEntity> getAllMeasurements() {
        return repository.findAll();
    }

    public Map<String, Map<String, MethodInfoTO>> getClassInfo() {
        return map(repository.findAll());
    }

    public Map<String, Map<String, MethodInfoTO>> getClassInfo(String className) {
        return map(repository.findAllByClassName(className));
    }

    public Map<String, Map<String, MethodInfoTO>> map(List<ExecutionTimeEntity> executionTimes) {
        Map<String, Map<String, MethodInfoTO>> result = new HashMap<>();
        for (var executionTime : executionTimes) {
            Map<String, MethodInfoTO> methodInfoTOMap;
            int numberOfCalls = 1;
            long totalTime = executionTime.getDuration();
            double averageTime = totalTime;

            if (result.containsKey(executionTime.getClassName())) {
                methodInfoTOMap = result.get(executionTime.getClassName());

                if (methodInfoTOMap.containsKey(executionTime.getMethodName())) {
                    MethodInfoTO infoTO = methodInfoTOMap.get(executionTime.getMethodName());

                    numberOfCalls += infoTO.getNumberOfCalls();
                    totalTime += infoTO.getTotalTime();
                    averageTime = (double) totalTime / numberOfCalls;
                }
            } else {
                methodInfoTOMap = new HashMap<>();
                result.put(executionTime.getClassName(), methodInfoTOMap);
            }

            MethodInfoTO to = MethodInfoTO.builder()
                    .numberOfCalls(numberOfCalls)
                    .averageTime(averageTime)
                    .totalTime(totalTime)
                    .build();

            methodInfoTOMap.put(executionTime.getMethodName(), to);
        }

        return result;
    }
}
