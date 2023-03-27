package com.casemd6_be;

import com.casemd6_be.model.Job;
import com.casemd6_be.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class CaseMd6BeApplication {

    @Autowired
    JobService jobService;

    public static void main(String[] args) {
        SpringApplication.run(CaseMd6BeApplication.class, args);
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void lockExpiredJobs() {
        List<Job> jobs = jobService.findAll();
        for (Job job : jobs) {
            if (new Date().after(job.getExpiredDate())) {
                job.setStatus(3);
                jobService.save(job);
            }
        }
    }
}
