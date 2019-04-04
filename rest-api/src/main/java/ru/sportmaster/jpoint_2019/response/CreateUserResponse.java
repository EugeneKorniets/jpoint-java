package ru.sportmaster.jpoint_2019.response;

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
