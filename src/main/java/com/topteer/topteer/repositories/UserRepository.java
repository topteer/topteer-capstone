package com.topteer.topteer.repositories;


import com.topteer.topteer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    default List<User> findAllByUsernameContaining(String username) {
        return null;
    }
}
