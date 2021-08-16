package com.topteer.topteer.repositories;

import com.topteer.topteer.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface EventRepository extends JpaRepository<Events, Long> {
    List<Events> findAllByTitleContaining(String query);

    Collection<? extends Events> findAllByUserId(long id);
}
