package com.hao.quartz.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerKey.triggerKey;

public class InvokeStatScheduleNoSpring {
    public void start() throws SchedulerException {

        JobDetail job = JobBuilder.newJob(InvokeStatJob.class)
                .withIdentity("myJob")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(3)
                    .repeatForever())
                .startAt(new Date())
                .build();


        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();

//
//        //InvokeStatJob是实现了org.quartz.Job的类
//        JobDetail jobDetail = new JobDetail("jobDetail", "jobDetailGroup", InvokeStatJob.class);
//        CronTrigger cronTrigger = new CronTrigger("cronTrigger", "triggerGroup");
//        try {
//            CronExpression cexp = new CronExpression("/3 * * * * ?");
//            cronTrigger.setCronExpression(cexp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//        scheduler.start();
    }

    public static void main(String[] args) {
        InvokeStatScheduleNoSpring invokeStatScheduleNoSpring = new InvokeStatScheduleNoSpring();
        try {
            invokeStatScheduleNoSpring.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
