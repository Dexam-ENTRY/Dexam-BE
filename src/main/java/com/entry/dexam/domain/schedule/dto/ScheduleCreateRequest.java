package com.entry.dexam.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ScheduleCreateRequest {
    private String title;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String target;
    private Integer targetGrade; //target이 ALL일경우 Null
    private Integer targetClassNo; //target이 ALL 또는 GRADE일 경우 Null
}
