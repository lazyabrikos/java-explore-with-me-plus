package ru.practicum.errors.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
public class ApiError {
    private String errors;
    private String message;
    private String reason;
    private HttpStatus status;
    private Instant timestamp;

    public ApiError(String errors, String message, String reason, HttpStatus status) {
        this.errors = errors;
        this.message = message;
        this.reason = reason;
        this.status = status;
        timestamp = Instant.now();
    }
}