package com.entry.dexam.domain.schedule.dto.response;

import java.util.List;

public record ScheduleListResponse(
        List<ScheduleItemResponse> items
) {
    public static ScheduleListResponse from(List<ScheduleItemResponse> items) {
        return new ScheduleListResponse(items);
    }
}