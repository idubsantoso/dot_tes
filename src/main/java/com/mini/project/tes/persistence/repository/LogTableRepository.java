package com.mini.project.tes.persistence.repository;

import com.mini.project.tes.domain.LogTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LogTableRepository extends JpaRepository<LogTableEntity, UUID>, JpaSpecificationExecutor<LogTableEntity> {
}
