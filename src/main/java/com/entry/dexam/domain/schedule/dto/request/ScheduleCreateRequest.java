package com.entry.dexam.domain.schedule.dto.request;

import com.entry.dexam.domain.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ScheduleCreateRequest (

    @NotBlank
    String title,
    @NotBlank
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


