package com.topteer.topteer.repositories;

import com.topteer.topteer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventVolunteerRepo extends JpaRepository <User, Long> {

}
