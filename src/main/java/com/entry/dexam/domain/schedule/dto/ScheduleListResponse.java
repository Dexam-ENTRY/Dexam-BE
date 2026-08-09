package com.entry.dexam.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleListResponse {

    private List<ScheduleItemResponse> items;
}