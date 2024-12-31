package ru.practicum.hits;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.hits.service.HitService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class HitsController {

    private final HitService hitService;

    public HitsController(HitService hitService) {
        this.hitService = hitService;
    }

    @PostMapping
    public ResponseEntity<Void> addHit(@RequestBody HitDto hitDto) {
        hitService.addHit(hitDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StatsDto>> getStats(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(required = false) Boolean unique) {

        List<StatsDto> stats = hitService.getStats(start, end, uris, unique);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
