package com.entry.dexam.domain.evaluation.dto;

import com.entry.dexam.domain.auth.dto.ClassRequest;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EvaluationAddRequest(
    @NotNull(message = "평가 유형은 필수입니다.")
    EvaluationType type,

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
    String title,

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 1000, message = "내용은 1000자 이하로 입력해주세요.")
    String content,

    @NotNull(message = "대상 반은 필수입니다.")
    @Valid
    ClassRequest target,

    @NotNull(message = "날짜는 필수입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date
) {
}
