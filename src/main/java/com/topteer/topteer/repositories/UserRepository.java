package com.topteer.topteer.repositories;


import com.topteer.topteer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

}
