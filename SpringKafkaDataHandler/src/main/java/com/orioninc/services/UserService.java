package com.orioninc.services;

import com.orioninc.models.User;
import com.orioninc.models.UserEvent;
import com.orioninc.models.UserIntervalData;
import com.orioninc.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.*;

@Service
public class UserService {
    @Autowired
    KafkaTemplate<User, UserIntervalData> kafkaTemplateJson;

    @Autowired
    ApplicationProperties applicationProperties;

    Map<User, List<UserEvent>> usersEvents = Collections.synchronizedMap(new HashMap<>());

    public void process(User user, UserEvent userEvent) {
        if(!usersEvents.containsKey(user)) {
            List<UserEvent> userEvents = new ArrayList<>();
            userEvents.add(userEvent);

            usersEvents.put(user, userEvents);

            Thread interval = new Thread(() -> {
                try {
                    Thread.sleep(applicationProperties.getInterval());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<UserEvent> resultForInterval = usersEvents.get(user);
                processData(user, resultForInterval);
            });
        } else {
            usersEvents.get(user).add(userEvent);
        }
    }

    void processData(User user, List<UserEvent> userEvents) {
        sendToSecondTopic(user, new UserIntervalData());
    }

    void sendToSecondTopic(User user, UserIntervalData userIntervalData) {
        ListenableFuture<SendResult<User, UserIntervalData>> listenableFuture = kafkaTemplateJson.sendDefault(user, userIntervalData);
        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed to send");
            }

            @Override
            public void onSuccess(SendResult<User, UserIntervalData> result) {
                System.out.println("Success sent to \"topic2\"");
            }
        });
    }
}
