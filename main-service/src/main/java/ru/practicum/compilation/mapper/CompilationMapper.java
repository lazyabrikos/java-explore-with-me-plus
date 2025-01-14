package ru.practicum.compilation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.compilation.dto.CompilationRequestDto;
import ru.practicum.compilation.dto.CompilationResponseDto;
import ru.practicum.compilation.model.Compilation;
import ru.practicum.event.mapper.EventMapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CompilationMapper {
    @Autowired
    protected EventMapper eventMapper;

    @Mapping(target = "title", source = "request.title")
    @Mapping(target = "pinned", source = "request.pinned")
    public abstract Compilation fromDto(CompilationRequestDto request);

    @Mapping(target = "id", source = "compilation.id")
    @Mapping(target = "title", source = "compilation.title")
    @Mapping(target = "pinned", source = "compilation.pinned")
    public abstract CompilationResponseDto toDto(Compilation compilation);

    public List<CompilationResponseDto> toDtoList(List<Compilation> compilations) {
        List<CompilationResponseDto> compilationResponseDtos = new ArrayList<>();
        for (Compilation compilation : compilations) {
            CompilationResponseDto dto = toDto(compilation);
            dto.setEvents(compilation.getEvents().stream()
                    .map(event -> eventMapper.toEventShortDto(event))
                    .toList()
            );
            compilationResponseDtos.add(dto);
        }

        return compilationResponseDtos;
    }
}
