package ru.sportmaster.jpoint_2019.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    private final String code;
    private final String message;
}
