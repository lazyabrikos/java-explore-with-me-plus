package ru.practicum.errors.exceptions;

public class DataConflictRequest extends RuntimeException {
    public DataConflictRequest(String message) {
        super(message);
    }
}
