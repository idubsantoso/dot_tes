package com.mini.project.tes.repository;

import com.mini.project.tes.model.entity.LogTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface LogTableRepository extends JpaRepository<LogTableEntity, UUID>, JpaSpecificationExecutor<LogTableEntity> {
}
