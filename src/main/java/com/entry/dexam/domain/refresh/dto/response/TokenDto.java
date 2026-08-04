package com.entry.dexam.domain.refresh.dto.response;

public record TokenDto(
        String refreshToken,
        String accessToken
) {
}
