package ru.practicum;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatDto {
    String app;
    String uri;
    String ip;
    LocalDateTime timestamp;
}
