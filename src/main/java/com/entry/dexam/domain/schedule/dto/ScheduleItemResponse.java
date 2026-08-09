package com.entry.dexam.domain.schedule.dto;

import com.entry.dexam.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleItemResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDate date;

    public static ScheduleItemResponse from(Schedule schedule) {
        return new ScheduleItemResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getDate()
        );
    }
}