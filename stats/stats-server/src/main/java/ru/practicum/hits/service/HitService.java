package ru.practicum.hits.service;

import ru.practicum.hits.HitDto;
import ru.practicum.hits.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface HitService {
    void addHit(HitDto hitDto);

    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
