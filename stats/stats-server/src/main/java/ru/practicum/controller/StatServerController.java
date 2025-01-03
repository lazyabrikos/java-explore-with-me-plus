package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.HitRequestDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.service.StatService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatServerController {
    private final StatService statService;

    @PostMapping("/hit")
    public StatsResponseDto save(@RequestBody HitRequestDto body) {
        return statService.save(body);
    }

    @GetMapping("/stats")
    public List<StatsResponseDto> getStats(@RequestParam(value = "start", required = true) String start,
                                     @RequestParam(value = "end", required = true) String end,
                                     @RequestParam(value = "uris", required = false) List<String> uris,
                                     @RequestParam(value = "unique", defaultValue = "false") Boolean unique) {

        return statService.getStats(start, end, uris, unique);
    }
}
