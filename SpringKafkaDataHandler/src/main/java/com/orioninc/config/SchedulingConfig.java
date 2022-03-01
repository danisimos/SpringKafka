package com.orioninc.config;

import com.orioninc.services.impl.SubscriptionsSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {
    @Autowired
    SubscriptionsSchedulingService subscriptionsSchedulingService;

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask((Runnable) () -> subscriptionsSchedulingService.interval(),
                triggerContext -> {
                    Date date = subscriptionsSchedulingService.getNextExecutionDate(triggerContext.lastCompletionTime());
                    System.out.println(date);
            return date;
        });
    }
}
