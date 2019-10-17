package ru.sportmaster.smlab.quiz.response;

import lombok.Data;

@Data
public class SuccessOrErrorResponse {

    private boolean success;
    private Error error;
}
