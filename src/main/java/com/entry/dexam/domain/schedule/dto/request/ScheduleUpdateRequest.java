package com.entry.dexam.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ScheduleUpdateRequest(

        @NotBlank
        String title,
        @NotBlank
        String content,
        LocalDate date,
        String target,
        Integer targetGrade,
        Integer targetClassNo
) {
}