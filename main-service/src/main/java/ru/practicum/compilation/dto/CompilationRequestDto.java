package ru.practicum.compilation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CompilationRequestDto {
    @Size(min = 1, max = 50)
    @NotNull
    @NotBlank
    private String title;
    private Boolean pinned;
    private List<Long> eventIds;
}
