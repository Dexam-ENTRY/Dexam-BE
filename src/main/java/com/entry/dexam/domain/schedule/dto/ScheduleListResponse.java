package com.entry.dexam.domain.schedule.dto;

import java.util.List;

public record ScheduleListResponse(
        List<ScheduleItemResponse> items
) {
    // 선택 사항: 생성 편의를 위한 static factory 메서드
    public static ScheduleListResponse from(List<ScheduleItemResponse> items) {
        return new ScheduleListResponse(items);
    }
}