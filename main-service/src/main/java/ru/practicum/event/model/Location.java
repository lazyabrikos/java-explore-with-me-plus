package ru.practicum.event.model;

import jakarta.persistence.Embedded;
import lombok.Data;

@Embedded
@Data
public class Location {
    float lat;
    float lon;
}
