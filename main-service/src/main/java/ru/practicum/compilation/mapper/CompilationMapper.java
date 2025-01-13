package ru.practicum.compilation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.compilation.dto.CompilationRequestDto;
import ru.practicum.compilation.dto.CompilationResponseDto;
import ru.practicum.compilation.model.Compilation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompilationMapper {
    @Mapping(target = "title", source = "request.title")
    @Mapping(target = "pinned", source = "request.pinned")
    Compilation fromDto(CompilationRequestDto request);

    @Mapping(target = "id", source = "compilation.id")
    @Mapping(target = "title", source = "compilation.title")
    @Mapping(target = "pinned", source = "compilation.pinned")
    CompilationResponseDto toDto(Compilation compilation);

    List<CompilationResponseDto> toDtoList(List<Compilation> compilations);
}
