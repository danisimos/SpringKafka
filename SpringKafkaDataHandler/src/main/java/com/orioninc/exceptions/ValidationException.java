package com.orioninc.exceptions;

public class ValidationException extends RuntimeException {
    private final ErrorEntityEnum errorEntityEnum;

    public ValidationException(ErrorEntityEnum errorEntityEnum) {
        super(errorEntityEnum.getMessage());
        this.errorEntityEnum = errorEntityEnum;
    }
}
