package com.topteer.topteer.repositories;

import com.topteer.topteer.models.EventVolunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface EventVolunteerRepository extends JpaRepository <EventVolunteer, Long> {

}
