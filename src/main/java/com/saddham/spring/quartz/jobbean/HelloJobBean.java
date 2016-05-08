package com.saddham.spring.quartz.jobbean;

import java.util.TimeZone;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by saddhamp on 26/4/16.
 */
public class HelloJobBean extends QuartzJobBean {
    private String firstName;
    private String lastName;

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            System.out.println("Hello " + firstName + " " + lastName);
        } catch (Exception exception){
            //Handle exception and reschedule the job
            Scheduler scheduler = context.getScheduler();
            TriggerKey currentTriggerKey = context.getTrigger().getKey();
            Trigger newTrigger = newTrigger()
                    .withIdentity(currentTriggerKey)
                    .forJob(context.getJobDetail())
                    .startNow()
                    //.startAt(evenHourDate(null))
                    //Every 5 seconds after 7:12 PM IST
                    .withSchedule(cronSchedule("0/5 12 19 * * ?").inTimeZone(TimeZone.getTimeZone("IST")))
                    //.modifiedByCalendar("HolidayCalendar")
                    .withPriority(5)
                    .build();

            try {
                scheduler.rescheduleJob(currentTriggerKey, newTrigger);
            } catch (SchedulerException schedulerException) {
                throw new RuntimeException("Could not reschedule job: " + context.getJobDetail(), schedulerException);
            }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
