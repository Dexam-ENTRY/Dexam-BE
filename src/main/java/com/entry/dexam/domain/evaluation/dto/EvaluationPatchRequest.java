package com.entry.dexam.domain.evaluation.dto;

import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EvaluationPatchRequest(
    EvaluationType type,

    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
    String title,

    @Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
    String content,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date
) {
}
