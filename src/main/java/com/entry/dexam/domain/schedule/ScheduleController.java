package com.entry.dexam.domain.schedule;

import com.entry.dexam.domain.schedule.dto.ScheduleCreateRequest;
import com.entry.dexam.domain.schedule.dto.ScheduleUpdateRequest;
import com.entry.dexam.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createSchedule(@RequestBody ScheduleCreateRequest request) {
        return scheduleService.createSchedule(request);
    }

    @PutMapping("/{scheduleId}")
    public ApiResponse<Void> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        scheduleService.updateSchedule(scheduleId, request);
        return ApiResponse.ok();
    }
    @DeleteMapping("/{scheduleId}")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ApiResponse.ok();
    }
}