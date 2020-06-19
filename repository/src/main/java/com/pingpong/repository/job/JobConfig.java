package com.pingpong.repository.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class JobConfig {

    private static final String JOB_IDENTITY = "job_update_mongo";
    private static final String JOB_DESCRIPTION = "Atualiza o mongo com os dados do H2o";

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(UpdateMongoJob.class)
                .storeDurably()
                .withIdentity(JOB_IDENTITY)
                .withDescription(JOB_DESCRIPTION)
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity(JOB_IDENTITY)
                .withDescription(JOB_DESCRIPTION)
                .withSchedule(simpleSchedule().repeatForever().withIntervalInMinutes(1))
                .build();
    }

}
