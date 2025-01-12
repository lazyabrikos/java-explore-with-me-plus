package ru.practicum.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilation.dto.CompilationResponseDto;
import ru.practicum.compilation.service.CompilationService;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/compilations")
public class PublicCompilationController {
    private final CompilationService service;

    @GetMapping
    public CompilationResponseDto get(@RequestParam(value = "pinned", required = false) Boolean pinned,
                                      @RequestParam(value = "from", required = false, defaultValue = "0") Integer from,
                                      @RequestParam(value = "size", required = false,
                                              defaultValue = "10") Integer size) {
        return service.get(pinned, from, size);
    }

    @GetMapping("/{id}")
    public CompilationResponseDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}
