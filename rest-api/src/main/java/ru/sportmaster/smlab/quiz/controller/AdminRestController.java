package ru.sportmaster.smlab.quiz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.sportmaster.smlab.quiz.exception.UserCreationException;
import ru.sportmaster.smlab.quiz.exception.UserNotFoundException;
import ru.sportmaster.smlab.quiz.request.CreateUserRequest;
import ru.sportmaster.smlab.quiz.request.FindUserRequest;
import ru.sportmaster.smlab.quiz.request.UpdateUserRequest;
import ru.sportmaster.smlab.quiz.response.CreateUserResponse;
import ru.sportmaster.smlab.quiz.response.Error;
import ru.sportmaster.smlab.quiz.response.SuccessOrErrorResponse;
import ru.sportmaster.smlab.quiz.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Object list(@RequestParam(required = false) String term) {

        log.info("Getting user list for admin page request");
        try {

            final FindUserRequest findUserRequest = new FindUserRequest();
            findUserRequest.setSort(new Sort(Sort.Direction.ASC, "nickname"));
            if (term != null) {
                findUserRequest.setTerm(term.toLowerCase());
            }

            return userService.find(findUserRequest);
        } catch (Exception e) {
            log.info("Error while getting user lists for admin page", e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public Object create(@Valid @RequestBody CreateUserRequest createUserRequest, HttpServletResponse httpServletResponse) {

        log.info("Getting create user request={}", createUserRequest);
        final CreateUserResponse createUserResponse = new CreateUserResponse();
        try {
            final int id = userService.create(createUserRequest);
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
            createUserResponse.setId(id);

        } catch (UserCreationException e) {
            log.info("Error during creating user for request={}", createUserRequest, e);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            createUserResponse.setError(new Error(String.valueOf(e.getCode()), e.getMessage()));
        }
        return createUserResponse;
    }

    @PostMapping("/update")
    public Object udpate(@Valid @RequestBody UpdateUserRequest updateUserRequest, HttpServletResponse httpServletResponse) {

        log.info("Getting user update request={}", updateUserRequest);
        final SuccessOrErrorResponse successOrErrorResponse = new SuccessOrErrorResponse();
        try {

            userService.update(updateUserRequest);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            successOrErrorResponse.setSuccess(true);

        } catch (Exception e) {
            log.error("Error while updating user for request={}", updateUserRequest, e);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            if (e instanceof UserNotFoundException) {
                successOrErrorResponse.setError(new Error(String.valueOf(0), e.getMessage()));
            } else {
                successOrErrorResponse.setError(new Error("unknown", "Unknown error"));
            }

        }
        return successOrErrorResponse;
    }

}
