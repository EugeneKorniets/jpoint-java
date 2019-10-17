package ru.sportmaster.smlab.quiz.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateUserRequest {

    @Positive
    private Integer id;

    private String name;

    private String nickname;

    private String surname;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{11,}$")
    private String phone;

    @Positive
    private Integer score;

    private Boolean active;

}
