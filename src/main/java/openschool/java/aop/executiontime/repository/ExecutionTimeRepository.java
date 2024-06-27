package openschool.java.aop.executiontime.repository;

import openschool.java.aop.executiontime.domain.ExecutionTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExecutionTimeRepository extends JpaRepository<ExecutionTimeEntity, UUID> {
}
