package ru.practicum.compilation.service;

import org.springframework.stereotype.Service;
import ru.practicum.compilation.dto.CompilationRequestDto;
import ru.practicum.compilation.dto.CompilationResponseDto;

@Service
public class CompilationServiceImpl implements CompilationService {
    @Override
    public CompilationResponseDto create(CompilationRequestDto request) {
        return null;
    }

    @Override
    public CompilationResponseDto update(CompilationRequestDto request, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public CompilationResponseDto get(Boolean pinned, Integer from, Integer size) {
        return null;
    }

    @Override
    public CompilationResponseDto getById(Integer id) {
        return null;
    }
}
