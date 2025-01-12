package ru.practicum.compilation.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompilationResponseDto {
    private Integer id;
    private Boolean pinned;
    private String title;
    private List<EventShortDto> events;
}
