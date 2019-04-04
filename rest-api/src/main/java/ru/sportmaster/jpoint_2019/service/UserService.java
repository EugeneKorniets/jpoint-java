package ru.sportmaster.jpoint_2019.service;

import ru.sportmaster.jpoint_2019.exception.UserCreationException;
import ru.sportmaster.jpoint_2019.jpa.entity.User;
import ru.sportmaster.jpoint_2019.request.CreateUserRequest;
import ru.sportmaster.jpoint_2019.request.FindUserRequest;
import ru.sportmaster.jpoint_2019.request.UpdateUserRequest;

import java.util.Collection;

public interface UserService {

    User findById(int id);

    int create(CreateUserRequest createUserRequest) throws UserCreationException;

    void update(UpdateUserRequest updateUserRequest) throws Exception;

    Collection<User> find(FindUserRequest findUserRequest);

}
