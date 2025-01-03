package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.HitRequestDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.mappers.StatsMapper;
import ru.practicum.model.Hit;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatService {
    private final StatRepository repository;
    private final StatsMapper mapper;


    @Override
    public StatsResponseDto save(HitRequestDto body) {
        Hit hit = new Hit();
        hit.setApp(body.getApp());
        hit.setUri(body.getUri());
        hit.setTimestamp(LocalDateTime.now());
        hit.setIp(body.getIp());

        return mapper.toDto(repository.save(hit));
    }

    @Override
    public List<StatsResponseDto> getStats(LocalDateTime start,
                                     LocalDateTime end,
                                     List<String> uris,
                                     Boolean unique) {
        List<StatsResponseDto> hits;

        if (unique && !uris.isEmpty()) {
            hits = repository.getStatsByUriWithUniqueIps(uris, start, end);
        } else if (unique && uris != null) {
            hits = repository.getStatsByTimeWithUniqueIps(start, end);
        } else if (!unique && uris != null) {
            hits = repository.getStatsByUri(uris, start, end);
        } else {
            hits = repository.findAllTimestampBetweenStartAndEnd(start, end);
        }

        return hits;
    }
}
