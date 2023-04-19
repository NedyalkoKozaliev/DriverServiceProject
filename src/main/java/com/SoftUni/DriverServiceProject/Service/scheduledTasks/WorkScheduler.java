package com.SoftUni.DriverServiceProject.Service.scheduledTasks;

import com.SoftUni.DriverServiceProject.Service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WorkScheduler {


    private final DriverService driverService;


    private Logger LOGGER = LoggerFactory.getLogger(WorkScheduler.class);

    public WorkScheduler(DriverService driverService) {
        this.driverService = driverService;
    }


    @Scheduled(cron="0 20 * * * ?")
    public void endOfWorkingTime() {
        driverService.findAll().forEach(e->{e.setAvailable(false);});


        LOGGER.info("End of Workday!");

    }

    @Scheduled(cron="0 8 * * * ?")
    public void startOfWorkingTime(){
        driverService.findAll().forEach(d->{d.setAvailable(true);});


        LOGGER.info("Start of workDay!");

    }


}
