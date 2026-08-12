package com.entry.dexam.domain.schedule;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.schedule.dto.ScheduleCreateRequest;
import com.entry.dexam.domain.schedule.dto.ScheduleListResponse;
import com.entry.dexam.domain.schedule.dto.ScheduleUpdateRequest;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserRepository userRepository;

    @PostMapping("/api/admin/schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createSchedule(@RequestBody ScheduleCreateRequest request) {
        return ApiResponse.ok(scheduleService.createSchedule(request));
    }

    @PutMapping("/api/admin/schedules/{scheduleId}")
    public ApiResponse<Void> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        scheduleService.updateSchedule(scheduleId, request);
        return ApiResponse.ok();
    }
    @DeleteMapping("/api/admin/schedules/{scheduleId}")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ApiResponse.ok();
    }
    @GetMapping("/api/admin/schedules")
    public ApiResponse<ScheduleListResponse> getSchedules(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Integer grade,
            @RequestParam(name = "class", required = false) Integer classNo
    ) {
        ScheduleListResponse response = scheduleService.getSchedules(startDate, endDate, grade, classNo);
        return ApiResponse.ok(response);
    }

    @GetMapping("/api/schedules")
    public ApiResponse<ScheduleListResponse> getAdminSchedules(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            User user = userRepository.findByIdWithClassInfo(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            ClassInfo classInfo = user.getClassInfo();
    ) {
        ScheduleListResponse response = scheduleService.getSchedules(startDate, endDate, , );
        return ApiResponse.ok(response);
    }
}