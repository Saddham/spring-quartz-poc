package com.saddham.spring.quartz.triggerbean;

import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * Needed to set Quartz useProperties=true when using Spring classes,
 * because Spring sets an object reference on JobDataMap that is not a String
 *
 * Created by saddhamp on 27/4/16.
 */
public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {
    private static final String JOB_DETAIL_KEY = "jobDetail";
    @Override
    public void afterPropertiesSet() throws ParseException {
        super.afterPropertiesSet();

        //Remove the JobDetail element
        getJobDataMap().remove(JOB_DETAIL_KEY);
    }
}
