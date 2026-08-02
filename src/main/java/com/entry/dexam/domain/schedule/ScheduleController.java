package com.entry.dexam.domain.schedule;

import com.entry.dexam.domain.schedule.dto.ScheduleCreateRequest;
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
}