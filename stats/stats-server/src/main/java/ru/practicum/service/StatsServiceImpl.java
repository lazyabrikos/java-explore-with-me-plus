package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.HitRequestDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.ViewStats;
import ru.practicum.mappers.StatsMapper;
import ru.practicum.model.Hit;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatService {
    private final StatRepository repository;
    private final StatsMapper mapper;

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);


    @Override
    public StatsResponseDto save(HitRequestDto body) {
        LocalDateTime timestamp = getDateTime(body.getTimestamp());
        Hit hit = new Hit();
        hit.setApp(body.getApp());
        hit.setUri(body.getUri());
        hit.setTimestamp(timestamp);
        hit.setIp(body.getIp());

        return mapper.toDto(repository.save(hit));
    }

    @Override
    public List<StatsResponseDto> getStats(String pathStart,
                                     String pathEnd,
                                     List<String> uris,
                                     Boolean unique) {
        List<StatsResponseDto> hits;
        LocalDateTime start = getDateTime(pathStart);
        LocalDateTime end = getDateTime(pathEnd);
        log.info("Uris = {}", uris);
        if (uris != null && !uris.isEmpty()) {
            if (unique) {
                hits = repository.getStatsByUriWithUniqueIps(uris, start, end);
            } else {
                hits = repository.getStatsByUri(uris, start, end);
            }
        } else {
            if (unique) {
                hits = repository.getStatsByTimeWithUniqueIps(start, end);
            } else {
                hits = repository.findAllTimestampBetweenStartAndEnd(start, end);
            }
        }

        return hits;
    }

    private LocalDateTime getDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    public Map<Long, Long> getView(List<Long> eventsId, boolean unique) {
        log.info("Getting views for events: {}, unique: {}", eventsId, unique);

        Map<Long, Long> views = new HashMap<>();

        // Retrieve stats using the getStats method
        List<ViewStats> stats = getStats(eventsId, unique);

        for (ViewStats stat : stats) {
            String uriPath = stat.getUri();
            if (uriPath.startsWith("/events/")) {
                try {
                    Long id = Long.valueOf(uriPath.substring("/events/".length()));
                    Long hits = stat.getHits();
                    views.put(id, hits);
                } catch (NumberFormatException e) {
                    log.warn("Invalid event ID in URI: {}", uriPath);
                }
            }
        }

        log.info("Processed views: {}", views);
        return views;
    }

    public List<ViewStats> getStats(List<Long> eventsId, boolean unique) {
        log.info("Getting stats for events: {}, unique: {}", eventsId, unique);

        String start = LocalDateTime.now().minusYears(20).format(formatter);
        String end = LocalDateTime.now().plusYears(20).format(formatter);

        String[] uris = eventsId.stream()
                .map(id -> String.format("/events/%d", id))
                .toArray(String[]::new);

        try {
            List<ViewStats> stats = statsClient.getStats(start, end, uris, unique);
            log.info("Retrieved {} stats entries", stats.size());
            return stats;
        } catch (Exception e) {
            log.error("Error getting stats: ", e);
            return Collections.emptyList();
        }
    }
}
