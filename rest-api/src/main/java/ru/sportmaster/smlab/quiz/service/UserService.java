package ru.sportmaster.smlab.quiz.service;

import ru.sportmaster.smlab.quiz.exception.UserCreationException;
import ru.sportmaster.smlab.quiz.jpa.entity.User;
import ru.sportmaster.smlab.quiz.request.CreateUserRequest;
import ru.sportmaster.smlab.quiz.request.FindUserRequest;
import ru.sportmaster.smlab.quiz.request.UpdateUserRequest;

import java.util.Collection;

public interface UserService {

    User findById(int id);

    int create(CreateUserRequest createUserRequest) throws UserCreationException;

    void update(UpdateUserRequest updateUserRequest) throws Exception;

    Collection<User> find(FindUserRequest findUserRequest);

}
