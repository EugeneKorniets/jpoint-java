package ru.sportmaster.smlab.quiz.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateTelegramUserRequest {

    private String nickname;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotNull
    @Positive
    private Integer score;

    @Email
    private String email;

}
