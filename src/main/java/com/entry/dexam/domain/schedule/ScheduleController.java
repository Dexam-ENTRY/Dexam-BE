package com.entry.dexam.domain.schedule;

import com.entry.dexam.domain.schedule.dto.ScheduleCreateRequest;
import com.entry.dexam.domain.schedule.dto.ScheduleListResponse;
import com.entry.dexam.domain.schedule.dto.ScheduleUpdateRequest;
import com.entry.dexam.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

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
    @GetMapping
    public ApiResponse<ScheduleListResponse> getSchedules(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Integer grade,
            @RequestParam(name = "class", required = false) Integer classNo
    ) {
        ScheduleListResponse response = scheduleService.getSchedules(startDate, endDate, grade, classNo);
        return ApiResponse.ok(response);
    }
}