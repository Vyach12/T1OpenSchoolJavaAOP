package openschool.java.aop.executiontime.service;

import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.dto.ClassStatisticsTO;
import openschool.java.aop.executiontime.dto.ExecutionTimeStatisticsTO;
import openschool.java.aop.executiontime.dto.MethodStatisticsTO;
import openschool.java.aop.executiontime.repository.ExecutionTimeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExecutionTimeFindUseCase {

    private final ExecutionTimeRepository repository;

    public List<ExecutionTimeEntity> getAllMeasurements() {
        return repository.findAll();
    }

    public Set<ClassStatisticsTO> getClassInfo() {
        return transformToClassStatisticsTO(repository.findAll());
    }

    public Set<ClassStatisticsTO> getClassInfo(String className) {
        return transformToClassStatisticsTO(repository.findAllByClassName(className));
    }

    private Map<String, Map<String, List<ExecutionTimeEntity>>> groupByClassAndMethodNames(List<ExecutionTimeEntity> executionTimes) {
        return executionTimes.stream()
                .collect(Collectors.groupingBy(
                        ExecutionTimeEntity::getClassName,
                        Collectors.groupingBy(ExecutionTimeEntity::getMethodName)
                ));
    }

    private Set<MethodStatisticsTO> collectByMethod(Map<String, List<ExecutionTimeEntity>> methods) {
        Set<MethodStatisticsTO> methodStatistics = new HashSet<>();

        for (var methodEntry : methods.entrySet()) {
            String methodName = methodEntry.getKey();
            List<ExecutionTimeEntity> methodEntities = methodEntry.getValue();

            long totalDuration = 0;
            for (ExecutionTimeEntity entity : methodEntities) {
                totalDuration += entity.getDurationMs();
            }
            int numberOfCalls = methodEntities.size();
            double averageDuration = (double) totalDuration / numberOfCalls;

            methodStatistics.add(new MethodStatisticsTO(
                    methodName,
                    new ExecutionTimeStatisticsTO(numberOfCalls, totalDuration, averageDuration)
            ));
        }
        return methodStatistics;
    }

    private Set<ClassStatisticsTO> transformToClassStatisticsTO(List<ExecutionTimeEntity> executionTimes) {
        var groupedByClassAndMethod = groupByClassAndMethodNames(executionTimes);

        Set<ClassStatisticsTO> classStatistics = new HashSet<>();

        for (var classEntry : groupedByClassAndMethod.entrySet()) {
            String className = classEntry.getKey();
            Set<MethodStatisticsTO> methodStats = collectByMethod(classEntry.getValue());

            classStatistics.add(new ClassStatisticsTO(className, methodStats));
        }

        return classStatistics;
    }
}
