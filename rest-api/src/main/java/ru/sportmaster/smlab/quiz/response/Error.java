package ru.sportmaster.smlab.quiz.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    private final String code;
    private final String message;
}
