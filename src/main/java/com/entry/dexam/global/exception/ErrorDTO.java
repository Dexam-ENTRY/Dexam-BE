package com.entry.dexam.global.exception;

import lombok.Builder;

@Builder
public record ErrorDTO<T>(
        String code,
        String message,
        T detail
) {
}
