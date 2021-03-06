package com.topteer.topteer.repositories;


import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("from User u where u.events = :num")
    Events eventId(long num);

    default List<User> findAllByUsernameContaining(String username) {
        return null;
    }
}
