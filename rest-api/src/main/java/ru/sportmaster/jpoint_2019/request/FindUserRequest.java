package ru.sportmaster.jpoint_2019.request;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class FindUserRequest {

    private boolean activeOnly;
    private String term;
    private Sort sort;

}
