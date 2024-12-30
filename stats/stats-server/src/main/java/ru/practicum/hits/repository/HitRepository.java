package ru.practicum.hits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.hits.entity.Hit;
import ru.practicum.hits.entity.Stats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HitRepository extends JpaRepository<Hit, Integer> {
    @Query(value = "SELECT new ru.practicum.model.Stats(" +
            "app, uri, COUNT(ip) as total) " +
            "FROM Hits " +
            "WHERE moment between :start AND :end " +
            "GROUP BY app, uri " +
            "ORDER BY total DESC")
    List<Stats> findAll(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = "SELECT new ru.practicum.model.Stats(" +
            "app, uri, COUNT(DISTINCT ip) as total) " +
            "FROM Hits " +
            "WHERE moment between :start AND :end " +
            "GROUP BY app, uri " +
            "ORDER BY total DESC")
    List<Stats> findAllUnique(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = "SELECT new ru.practicum.model.Stats(" +
            "app, uri, COUNT(ip) as total) " +
            "FROM Hits " +
            "WHERE moment between :start AND :end " +
            "AND uri in ( :uris) " +
            "GROUP BY app, uri " +
            "ORDER BY total DESC")
    List<Stats> findUris(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uris
    );

    @Query(value = "SELECT new ru.practicum.model.Stats(" +
            "app, uri, COUNT(DISTINCT ip) as total) " +
            "FROM Hits " +
            "WHERE moment between :start AND :end " +
            "AND uri in ( :uris) " +
            "GROUP BY app, uri " +
            "ORDER BY total DESC")
    List<Stats> findUrisUnique(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uris") List<String> uris
    );
}