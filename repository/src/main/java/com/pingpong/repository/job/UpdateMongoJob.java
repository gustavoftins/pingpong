package com.pingpong.repository.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class UpdateMongoJob implements Job {

    private final UpdateMongoService updateMongoService;

    public UpdateMongoJob(UpdateMongoService updateMongoService) {
        this.updateMongoService = updateMongoService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executando job de atualização do mango");
        this.updateMongoService.updateMongo();
    }
}
