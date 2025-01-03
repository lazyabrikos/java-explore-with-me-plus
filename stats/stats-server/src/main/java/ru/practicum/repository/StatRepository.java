package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.StatsResponseDto;
import ru.practicum.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatRepository extends JpaRepository<Hit, Long> {

    @Query("select h.app," +
            "      h.uri," +
            "      COUNT(h.ip) as hits " +
            "      from Hit as h" +
            "      where h.timestamp between ?1 and ?2" +
            "      GROUP BY h.app, h.uri" )
    List<StatsResponseDto> findAllTimestampBetweenStartAndEnd(LocalDateTime start, LocalDateTime end);

    @Query("select h.app, " +
            "      h.uri, " +
            "      COUNT(h.ip) as hits from Hit as h" +
            " GROUP BY h.app, h.uri")
    List<StatsResponseDto> getStatsByUri(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query("select h.app, " +
            "      h.uri, " +
            "      COUNT(DISTINCT(h.ip)) as hits from Hit as h" +
            " WHERE h.uri IN  ?1 AND h.timestamp > ?2 AND h.timestamp < ?3" +
            " GROUP BY h.app, h.uri")
    List<StatsResponseDto> getStatsByUriWithUniqueIps(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query("select h.app, " +
            "      h.uri," +
            "      COUNT(DISTINCT(h.ip)) as hits from Hit as h" +
            " where h.timestamp > ?1 and h.timestamp < ?2 " +
            " group by h.app, h.uri")
    List<StatsResponseDto> getStatsByTimeWithUniqueIps(LocalDateTime start, LocalDateTime end);
}
