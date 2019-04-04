package ru.sportmaster.jpoint_2019.response;

import lombok.Data;

@Data
public class SuccessOrErrorResponse {

    private boolean success;
    private Error error;
}
