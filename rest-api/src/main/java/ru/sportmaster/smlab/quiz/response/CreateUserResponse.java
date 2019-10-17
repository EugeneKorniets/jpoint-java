package ru.sportmaster.smlab.quiz.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserResponse {

    private int id;
    private Error error;
}
