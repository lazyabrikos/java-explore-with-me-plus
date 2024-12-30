package ru.practicum.hits.mapper;

import ru.practicum.hits.HitDto;
import ru.practicum.hits.StatsDto;
import ru.practicum.hits.entity.Hit;
import ru.practicum.hits.entity.Stats;

public class Mapper {

    public static HitDto tiHitDto(Hit hit) {
        HitDto hitDto = new HitDto();
        hitDto.setIp(hit.getIp());
        hitDto.setApp(hit.getApp());
        hitDto.setUri(hit.getUri());
        hitDto.setTimestamp(hit.getMoment());
        return hitDto;
    }

    public static Hit toHit(HitDto hitDto) {
        Hit hit = new Hit();
        hit.setIp(hitDto.getIp());
        hit.setApp(hitDto.getApp());
        hit.setUri(hitDto.getUri());
        hit.setMoment(hitDto.getTimestamp());
        return hit;
    }

    public static StatsDto toResponseDto(Stats stats) {
        StatsDto statsDto = new StatsDto();
        statsDto.setApp(stats.getApp());
        statsDto.setHits(stats.getTotal());
        statsDto.setUri(stats.getUri());
        return statsDto;
    }
}
