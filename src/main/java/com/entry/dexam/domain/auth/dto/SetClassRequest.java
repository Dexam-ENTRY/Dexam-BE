package com.entry.dexam.domain.auth.dto;

import jakarta.validation.constraints.NotNull;

public record SetClassRequest(
    @NotNull(message = "빈 칸은 불가합니다.")
    int grade,

    @NotNull(message = "빈 칸은 불가합니다.")
    int classNum
) {
}
