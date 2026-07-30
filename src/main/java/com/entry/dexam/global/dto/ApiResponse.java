package com.entry.dexam.global.dto;

public record ApiResponse<T>(
        boolean success,
        T data
) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data);
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(true, null);
    }
}
