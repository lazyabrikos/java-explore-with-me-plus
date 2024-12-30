package ru.practicum;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class ResponseStatDto {
    String app;
    String uri;
    Long hits;
}
