package com.entry.dexam.global.exception;

public record FieldErrorDto(
        String field,
        String message
) {
}
