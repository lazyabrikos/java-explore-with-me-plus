package ru.practicum.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.event.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>{

    @Query("SELECT e FROM Event AS e" +
            " WHERE e.id IN ?1")
    List<Event> getAllByIds(List<Long> eventIds);
}
