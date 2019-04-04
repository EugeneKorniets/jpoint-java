package ru.sportmaster.jpoint_2019.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends Exception {

    private int id;

    public UserNotFoundException(int id) {
        this.id = id;
    }

    public UserNotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }

    public UserNotFoundException(String message, Throwable cause, int id) {
        super(message, cause);
        this.id = id;
    }

    public UserNotFoundException(Throwable cause, int id) {
        super(cause);
        this.id = id;
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int id) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.id = id;
    }
}
