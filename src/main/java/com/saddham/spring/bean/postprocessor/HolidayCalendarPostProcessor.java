package com.saddham.spring.bean.postprocessor;

import java.util.Date;

import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by saddhamp on 26/4/16.
 */
public class HolidayCalendarPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("holidayCalendar".equals(beanName)){
            HolidayCalendar holidayCalendar = (HolidayCalendar) bean;
            holidayCalendar.addExcludedDate(new Date());
            return holidayCalendar;
        }

        return bean;
    }
}
