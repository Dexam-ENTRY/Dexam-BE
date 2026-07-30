package com.entry.dexam.domain.auth.dto;

import jakarta.validation.constraints.Positive;

public record SetClassRequest(
    @Positive(message = "학년은 양수여야 합니다.")
    int grade,

    @Positive(message = "반은 양수여야 합니다.")
    int classNum
) {
}
