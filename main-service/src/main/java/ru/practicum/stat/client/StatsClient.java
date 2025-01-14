package ru.practicum.stat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.HitRequestDto;
import ru.practicum.ViewStats;

import java.util.List;

@FeignClient(value = "stats-client", url = "${ewm-stats-service.url}")
public interface StatsClient {

    @PostMapping("/hit")
    Object createStats(@RequestBody HitRequestDto creationDto);

    @GetMapping("/stats")
    List<ViewStats> getStats(@RequestParam String start,
                             @RequestParam String end,
                             @RequestParam(required = false) String[] uris,
                             @RequestParam(defaultValue = "false") boolean unique);
}