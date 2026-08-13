package com.entry.dexam.domain.schedule.dto;

import com.entry.dexam.domain.schedule.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record ScheduleCreateRequest (

    String title,
    String content,
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date,
    String target,
    Integer targetGrade,
    Integer targetClassNo
){
    public Schedule toEntity() {
        return Schedule.builder()
                .title(title())
                .content(content)
                .date(date)
                .target(target)
                .targetGrade(targetGrade)
                .targetClassNo(targetClassNo)
                .build();
    }
    }


