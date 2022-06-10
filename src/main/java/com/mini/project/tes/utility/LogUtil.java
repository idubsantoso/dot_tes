package com.mini.project.tes.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.domain.LogTableEntity;
import com.mini.project.tes.persistence.service.LogTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {
    @Autowired
    private LogTableService logService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void error(String event, String object) {
        LogTableEntity logTable = LogTableEntity.LogTableBuilder.aLogTable()
                .withEvent(event)
                .withMessage("Error")
                .withLogTime(CalendarUtil.GET_GMT7_TIMESTAMP())
                .withObject(object)
                .build();
        try {
            logService.save(logTable);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }

    public void success(String event, String object) {
        LogTableEntity logTable = LogTableEntity.LogTableBuilder.aLogTable()
                .withEvent(event)
                .withMessage("Success")
                .withLogTime(CalendarUtil.GET_GMT7_TIMESTAMP())
                .withObject(object)
                .build();
        try {
            logService.save(logTable);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
