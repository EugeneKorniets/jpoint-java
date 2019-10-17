package ru.sportmaster.smlab.quiz.exception;

import lombok.Getter;

@Getter
public class UserCreationException extends Exception {

    public static final String REASON_IS_NOT_KNOWN = "unknown";
    public static final String PHONE_NOT_UNIQUE = "phone";
    public static final String EMAIL_NOT_UNIQUE = "email";

    private String code;

    public UserCreationException(String code) {
        this.code = code;
    }

    public UserCreationException(String message, String code) {
        super(message);
        this.code = code;
    }

    public UserCreationException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public UserCreationException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public UserCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
