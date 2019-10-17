package ru.sportmaster.smlab.quiz.jpa.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ru.sportmaster.smlab.quiz.jpa.entity.User;

import java.util.Collection;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u from User u where (lower(u.nickname) like %?1% OR lower(u.surname) like %?1%) and u.processed <> true")
    Collection<User> findUsers(String term, Sort sort);

    @Query("SELECT u from User u where u.processed <> true")
    Collection<User> findUsers(Sort sort);

    @Query("SELECT u from User u where u.active=?1 and u.processed <> true")
    Collection<User> findUsers(boolean active, Sort sort);

    @Query("SELECT u from User u where (lower(u.nickname) like %?1% OR lower(u.surname) like %?1%) and u.active=?2 and u.processed <> true")
    Collection<User> findUsers(String term, boolean active, Sort sort);
}
