package com.entry.dexam.domain.schedule.dto.response;

import com.entry.dexam.domain.schedule.entity.Schedule;

import java.time.LocalDate;

public record ScheduleItemResponse(
        Long id,
        String title,
        String content,
        LocalDate date,
        Integer targetGrade,
        Integer targetClassNo
) {
    public static ScheduleItemResponse from(Schedule schedule) {
        return new ScheduleItemResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getDate(),
                schedule.getTargetGrade(),
                schedule.getTargetClassNo()
        );
    }
}