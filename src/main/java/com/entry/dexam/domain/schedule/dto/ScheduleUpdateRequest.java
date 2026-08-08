package com.entry.dexam.domain.schedule.dto;

import java.time.LocalDate;

public record ScheduleUpdateRequest(
        String title,
        String content,
        LocalDate date,
        String target,
        Integer grade,
        Integer classNo
) {
}