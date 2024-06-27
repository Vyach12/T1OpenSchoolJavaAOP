package openschool.java.aop.executiontime.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import openschool.java.aop.executiontime.repository.ExecutionTimeRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecutionTimeCreateUseCase {
    private final ExecutionTimeRepository repository;

    @Async
    @Transactional
    public void save(ExecutionTimeEntity entity) {
        repository.save(entity);
    }
}
