package ru.sportmaster.smlab.quiz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sportmaster.smlab.quiz.request.FindUserRequest;
import ru.sportmaster.smlab.quiz.service.UserService;

@RestController
@Slf4j
@CrossOrigin("*")
public class PublicRestController {

    private final UserService userService;

    @Autowired
    public PublicRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/results")
    public Object results() {

        log.info("Getting results request");
        try {
            final FindUserRequest findUserRequest = new FindUserRequest();
            findUserRequest.setSort(new Sort(Sort.Direction.DESC, "score"));
            findUserRequest.setActiveOnly(true);
            return userService.find(findUserRequest);
        } catch (Exception e) {
            log.info("Error while getting results", e);
            throw new RuntimeException(e);
        }
    }
}
