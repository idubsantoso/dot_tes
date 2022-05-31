package com.mini.project.tes.scheduler.scheduled;

import com.mini.project.tes.service.MovieListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MainScheduler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MovieListService service;

    @Value("${dot.jobs.scheduleJob.enable}")
    private boolean scheduleJobEnable;

    @Value("${dot.jobs.externalApi.enable}")
    private boolean externalApi;


    //    @Scheduled(cron= "${recurring.jobs.autoScheduleJob.schedule}")
    @Scheduled(fixedDelayString="${dot.jobs.scheduleJob.fixedRate}", zone="${dot.jobs.scheduleJob.zone}")
    public void scheduleDelete() {
        if (scheduleJobEnable){
            log.info("run schedule job");
            service.jobDelete();
        }
    }
//    @Scheduled(fixedDelayString="${dot.jobs.externalApi.fixedRate}", zone="${dot.jobs.externalApi.zone}")
    @Scheduled(cron= "${dot.jobs.externalApi.schedule}", zone="${dot.jobs.externalApi.zone}")
    public void scheduleExternalApis() {
        if (externalApi){
            log.info("run schedule external api");
            service.jobDelete();
        }
    }


}
