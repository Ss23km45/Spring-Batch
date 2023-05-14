package com.coding.ninja.spring.batch.SpringBatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
//@RequestMapping("/Consumer")
@EnableScheduling
public class ConsumerController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job;

    //@PostMapping("/UploadCsvFile")
    //@Scheduled(cron = "sec min hour dayofTheMonth Month dayOftheweek")
    //sec/10 means for every 10 sec
    //min/10 means for every 10 min
    // hours /10 means for every 10 hours
    @Scheduled(cron = "*/10 * * * * *")
    public void uploadCsvFile(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("JobstartTime", System.currentTimeMillis()).toJobParameters();

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobRestartException e) {
            throw new RuntimeException(e);
        }


        //return "Success";
    }
}
