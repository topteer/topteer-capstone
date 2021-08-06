package com.topteer.topteer.repositories;


import com.topteer.topteer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
