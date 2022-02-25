package com.orioninc.exceptions;

public enum ErrorEntityEnum {
    SUBSCRIPTION_NULL("Подписка не задана"),
    USER_NULL("Пользователь не задан"),
    USER_FIRSTNAME_NULL("Имя пользователя не задано"),
    USER_LASTNAME_NULL("Фамилия пользователя не задана"),
    USER_AGE_INVALID("Возраст пользователя не задан или неверный"),
    USER_FIRSTNAME_EMPTY("Задано пустое имя пользователя"),
    USER_LASTNAME_EMPTY("Задана пустая фамилия пользователя"),
    SUBSCRIPTION_TYPE_NULL("Тип подписки не задан"),
    SUBSCRIPTION_WEEK_COUNT_INVALID("Период подписки не задан или неверен");


    private final String message;

    ErrorEntityEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
