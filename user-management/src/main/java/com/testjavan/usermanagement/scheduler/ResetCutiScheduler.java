package com.testjavan.usermanagement.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResetCutiScheduler {

    private static final Logger log = LoggerFactory.getLogger(ResetCutiScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 0 ? * * 2022/1")
    public void schedulerReset() {

        log.info("The time is now {}", dateFormat.format(new Date()));

    }

}
