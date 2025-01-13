package ru.practicum.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilation.dto.CompilationRequestDto;
import ru.practicum.compilation.dto.CompilationResponseDto;
import ru.practicum.compilation.service.CompilationService;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/admin/compilations")
public class AdminCompilationController {
    private final CompilationService service;

    @PostMapping
    public CompilationResponseDto create(@RequestBody CompilationRequestDto body) {
        return service.create(body);
    }

    @PatchMapping("/{id}")
    public CompilationResponseDto update(@RequestBody CompilationRequestDto body, @PathVariable Long id) {
        return service.update(body, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
