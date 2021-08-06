package com.topteer.topteer.repositories;

import com.topteer.topteer.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, Long> {
}
