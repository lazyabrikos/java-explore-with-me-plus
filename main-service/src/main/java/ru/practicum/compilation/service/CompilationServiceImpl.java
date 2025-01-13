package ru.practicum.compilation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.compilation.dto.CompilationRequestDto;
import ru.practicum.compilation.dto.CompilationResponseDto;
import ru.practicum.compilation.mapper.CompilationMapper;
import ru.practicum.compilation.model.Compilation;
import ru.practicum.compilation.repository.CompilationRepository;
import ru.practicum.errors.exceptions.NotFoundException;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.mapper.EventMapper;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CompilationMapper compilationMapper;

    @Override
    public CompilationResponseDto create(CompilationRequestDto request) {
        Compilation compilation = compilationMapper.fromDto(request);

        List<Event> events = new ArrayList<>();

        if (request.getEventIds() != null) {
            events = eventRepository.getAllByIds(request.getEventIds());
        }

        compilation.setEvents(events);

        CompilationResponseDto dto = compilationMapper.toDto(compilation);
        dto.setEvents(new ArrayList<>());

        if (compilation.getEvents() != null) {
            dto.setEvents(eventMapper.toEventShortDtoList(events));
        }

        return dto;
    }

    @Override
    public CompilationResponseDto update(CompilationRequestDto request, Long id) {
        Compilation compilation = getCompilation(id);

        compilation.setTitle(request.getTitle());
        compilation.setPinned(request.getPinned());

        if (request.getEventIds() != null) {
            List<Event> events = eventRepository.getAllByIds(request.getEventIds());
            compilation.setEvents(events);
        }

        return compilationMapper.toDto(repository.save(compilation));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CompilationResponseDto> get(Boolean pinned, Integer offset, Integer size) {
        if (pinned) {
            return compilationMapper.toDtoList(repository.getPinnedCompilations(offset, size));
        }

        return compilationMapper.toDtoList(repository.getCompilations(size, offset));
    }

    @Override
    public CompilationResponseDto getById(Long id) {
        Compilation compilation = getCompilation(id);

        CompilationResponseDto responseDto = compilationMapper.toDto(compilation);
        responseDto.setEvents(new ArrayList<>());

        if (compilation.getEvents() != null) {
            List<EventShortDto> events = eventMapper.toEventShortDtoList(compilation.getEvents());
            responseDto.setEvents(events);
        }

        return responseDto;
    }

    private Compilation getCompilation(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("No such compilation with id: " + id));
    }
}
