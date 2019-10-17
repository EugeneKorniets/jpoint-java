package ru.sportmaster.smlab.quiz.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sportmaster.smlab.quiz.exception.UserCreationException;
import ru.sportmaster.smlab.quiz.exception.UserNotFoundException;
import ru.sportmaster.smlab.quiz.jpa.entity.User;
import ru.sportmaster.smlab.quiz.jpa.repository.UserRepository;
import ru.sportmaster.smlab.quiz.request.CreateUserRequest;
import ru.sportmaster.smlab.quiz.request.FindUserRequest;
import ru.sportmaster.smlab.quiz.request.UpdateUserRequest;

import java.util.Collection;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(int id) {

        log.info("Getting new find request by id={}", id);
        try {
            return userRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error("Error while trying to find user by id={}", id, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(CreateUserRequest createUserRequest) throws UserCreationException {

        log.info("Getting new createUser request={}", createUserRequest);

        final User newUser = new User();
        newUser.setName(createUserRequest.getName());
        newUser.setNickname(createUserRequest.getNickname());
        newUser.setSurname(createUserRequest.getSurname());
        newUser.setPhone(createUserRequest.getPhone());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setScore(0);
        newUser.setActive(true);
        newUser.setProcessed(false);

        try {
            final User savedUser = userRepository.save(newUser);
            log.info("User={} successfully created", savedUser);
            return savedUser.getId();
        } catch (Exception e) {
            log.error("Error while trying to save user for request={}", createUserRequest, e);
            final Optional<Throwable> throwableOptional = Optional.ofNullable(e.getCause());
            if (throwableOptional.isPresent()) {
                final Throwable throwable = throwableOptional.get();
                if (throwable instanceof ConstraintViolationException) {
                    final ConstraintViolationException violationException = (ConstraintViolationException) throwable;
                    if ((violationException).getConstraintName().contains("(EMAIL)")) {
                        throw new UserCreationException("Email не уникален", e, UserCreationException.EMAIL_NOT_UNIQUE);
                    } else if ((violationException).getConstraintName().contains("(PHONE)")) {
                        throw new UserCreationException("Телефон не уникален", e, UserCreationException.PHONE_NOT_UNIQUE);
                    } else {
                        throw new UserCreationException(e, UserCreationException.REASON_IS_NOT_KNOWN);
                    }
                } else {
                    throw new UserCreationException(e, UserCreationException.REASON_IS_NOT_KNOWN);
                }
            } else {
                throw new UserCreationException(e, UserCreationException.REASON_IS_NOT_KNOWN);
            }
        }
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) throws Exception {

        log.info("Getting new update user request={}", updateUserRequest);

        final User user = findById(updateUserRequest.getId());
        if (user == null) {
            log.error("User not found by id={}", updateUserRequest.getId());
            throw new UserNotFoundException("User not found by id=" + updateUserRequest.getId(), updateUserRequest.getId());
        }

        //prepare the update
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getNickname() != null) {
            user.setNickname(updateUserRequest.getNickname());
        }
        if (updateUserRequest.getPhone() != null) {
            user.setPhone(updateUserRequest.getPhone());
        }
        if (updateUserRequest.getName() != null) {
            user.setName(updateUserRequest.getName());
        }
        if (updateUserRequest.getSurname() != null) {
            user.setSurname(updateUserRequest.getSurname());
        }
        if (updateUserRequest.getActive() != null) {
            user.setActive(updateUserRequest.getActive());
        }
        if (updateUserRequest.getScore() != null) {
            user.setScore(updateUserRequest.getScore());
        }

        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error while trying to update user for request={}", updateUserRequest, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<User> find(FindUserRequest findUserRequest) {

        log.info("Getting new find request {}", findUserRequest);
        try {

            //analyze the request here
            final String term = findUserRequest.getTerm();
            final boolean activeOnly = findUserRequest.isActiveOnly();
            final Sort sort = findUserRequest.getSort();
            if (activeOnly) {
                if (term != null) {
                    return userRepository.findUsers(term, true, sort);
                } else {
                    return userRepository.findUsers(true, sort);
                }
            } else {
                if (term != null) {
                    return userRepository.findUsers(term, sort);
                } else {
                    return userRepository.findUsers(sort);
                }
            }
        } catch (Exception e) {
            log.error("Error while trying to find users fo request={}", findUserRequest, e);
            throw new RuntimeException(e);
        }
    }
}
