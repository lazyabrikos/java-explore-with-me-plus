package ru.practicum.hits;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HitDto {
    @NotBlank(message = "Название сервиса не может быть пустым")
    private String app;

    @NotBlank(message = "Название URI не может быть пустым")
    private String uri;

    @NotBlank(message = "IP-адрес не может быть пустым")
    private String ip;

    @NotNull(message = "Временная метка не может быть пустым")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
