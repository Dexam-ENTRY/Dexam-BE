package com.entry.dexam.domain.schedule.controller;

import com.entry.dexam.domain.schedule.dto.request.ScheduleCreateRequest;
import com.entry.dexam.domain.schedule.dto.response.ScheduleListResponse;
import com.entry.dexam.domain.schedule.dto.request.ScheduleUpdateRequest;
import com.entry.dexam.domain.schedule.service.ScheduleService;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/admin/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createSchedule(@RequestBody ScheduleCreateRequest request) {
        return ApiResponse.ok(scheduleService.createSchedule(request));
    }

    @PutMapping("/admin/schedules/{scheduleId}")
    public ApiResponse<Void> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        scheduleService.updateSchedule(scheduleId, request);
        return ApiResponse.ok();
    }
    @DeleteMapping("/admin/schedules/{scheduleId}")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ApiResponse.ok();
    }
    @GetMapping("/admin/schedules")
    public ApiResponse<ScheduleListResponse> getSchedules(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Integer grade,
            @RequestParam(name = "class", required = false) Integer classNo,
            @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        ScheduleListResponse response = scheduleService.getAdminSchedules(startDate, endDate, grade, classNo, userId);
        return ApiResponse.ok(response);
    }

    @GetMapping("/schedules")
    public ApiResponse<ScheduleListResponse> getAdminSchedules(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        ScheduleListResponse response = scheduleService.getSchedules(startDate, endDate, userId);
        return ApiResponse.ok(response);
    }
}