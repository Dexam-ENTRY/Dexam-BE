package com.entry.dexam.domain.evaluation.controller;

import com.entry.dexam.domain.evaluation.dto.EvaluationDto;
import com.entry.dexam.domain.evaluation.dto.EvaluationGetResponse;
import com.entry.dexam.domain.evaluation.service.PerformanceService;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performances")
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("/{performanceId}")
    public ApiResponse<EvaluationDto> readPerformance(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @PathVariable Long performanceId
    ){
        EvaluationDto response = performanceService.readPerformance(userId, performanceId);
        return ApiResponse.ok(response);

    }

    @GetMapping
    public ApiResponse<EvaluationGetResponse> readPerformanceList(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @RequestParam(required = false) String keyword
    ){
        EvaluationGetResponse response =
                performanceService.readPerformanceList(userId, keyword);
        return ApiResponse.ok(response);
    }
}
