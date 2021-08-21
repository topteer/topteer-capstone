package com.topteer.topteer.repositories;

import com.topteer.topteer.models.Events;
import com.topteer.topteer.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EventRepository extends JpaRepository<Events, Long> {
    List<Events> findAllByTitleContaining(String query);

    @Query("from Events ev where ev.title like %:query% or ev.location like %:query% or ev.description like %:query%")
    List<Events> findAllEventsByTitleOrLocationContaining(String query);

    Collection<? extends Events> findAllByUserId(long id);
}
