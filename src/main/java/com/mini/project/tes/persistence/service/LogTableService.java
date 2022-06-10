package com.mini.project.tes.persistence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.domain.LogTableEntity;

public interface LogTableService {
    public LogTableEntity save(LogTableEntity logTableEntity) throws JsonProcessingException;
}
