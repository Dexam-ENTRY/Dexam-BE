package com.entry.dexam.domain.auth.dto;

import jakarta.validation.constraints.Positive;

public record SetClassRequest(
    @Positive(message = "빈 칸은 불가합니다.")
    int grade,

    @Positive(message = "빈 칸은 불가합니다.")
    int classNum
) {
}
