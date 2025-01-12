package ru.practicum.compilation.service;

import ru.practicum.compilation.dto.CompilationRequestDto;
import ru.practicum.compilation.dto.CompilationResponseDto;

public interface CompilationService {

    CompilationResponseDto create(CompilationRequestDto request);

    CompilationResponseDto update(CompilationRequestDto request, Integer id);

    void delete(Integer id);

    CompilationResponseDto get(Boolean pinned, Integer from, Integer size);

    CompilationResponseDto getById(Integer id);
}
