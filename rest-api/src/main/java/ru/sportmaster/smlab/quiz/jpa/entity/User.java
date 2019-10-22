package ru.sportmaster.smlab.quiz.jpa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String nickname;
    private String name;
    private String surname;
    private String phone;
    private String email;

    private int score;

    /**
     * mean that user is active or not
     */
    private boolean active;

    /**
     * mean that user is processed and dont need to be include in any methods (admin methods too)
     */
    private boolean processed;

}
