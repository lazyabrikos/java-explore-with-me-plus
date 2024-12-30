package ru.practicum.hits.service;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import ru.practicum.hits.HitDto;
import ru.practicum.hits.StatsDto;
import ru.practicum.hits.entity.Stats;
import ru.practicum.hits.mapper.Mapper;
import ru.practicum.hits.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HitServiceImpl implements HitService {

    private final HitRepository hitRepository;

    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    @Override
    @Transactional
    public void addHit(HitDto hitDto) {
        hitRepository.save(Mapper.toHit(hitDto));
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (start.isAfter(end)) {
            throw new ValidationException("Время окончания позже начала");
        }

        List<Stats> statistic;
        if ((uris == null) || (uris.isEmpty())) {
            if (unique) {
                statistic = hitRepository.findAllUnique(start, end);
            } else {
                statistic = hitRepository.findAll(start, end);
            }
        } else {
            if (unique) {
                statistic = hitRepository.findUrisUnique(start, end, uris);
            } else {
                statistic = hitRepository.findUris(start, end, uris);
            }
        }
        return statistic.stream()
                .map(Mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
