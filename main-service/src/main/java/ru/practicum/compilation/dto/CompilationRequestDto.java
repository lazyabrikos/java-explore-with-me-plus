package ru.practicum.compilation.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompilationRequestDto {
    private String title;
    private Boolean pinned;
    private List<Integer> eventIds;
}
