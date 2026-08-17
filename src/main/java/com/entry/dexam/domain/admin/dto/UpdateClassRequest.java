package com.entry.dexam.domain.admin.dto;

import jakarta.validation.constraints.Positive;

public record UpdateClassRequest(
    @Positive(message = "학년은 양수여야 합니다.")
    int grade,

    @Positive(message = "반은 양수여야 합니다.")
    int classNum
) {
}
