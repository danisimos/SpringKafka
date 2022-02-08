package com.orioninc.services;

import com.orioninc.models.User;

public interface TopicListener {
    void listenJsonUsers(User user, String key, String topicName);
}
