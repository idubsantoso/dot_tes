package com.mini.project.tes.service.impl;

import com.mini.project.tes.model.entity.LogTableEntity;
import com.mini.project.tes.repository.LogTableRepository;
import com.mini.project.tes.service.LogTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LogTableServiceImpl implements LogTableService {
    @Autowired
    private LogTableRepository repo;
    @Override
    public LogTableEntity save(LogTableEntity logTable) {
        try {
            return repo.saveAndFlush(logTable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
