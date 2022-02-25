package com.orioninc.exceptions;

public enum ErrorEntityEnum {
    NULL_SUBSCRIPTION("Подписка не задана"),
    NULL_USER("Пользователь не задан");

    private final String message;

    ErrorEntityEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
