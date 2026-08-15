package com.entry.dexam.domain.evaluation.controller;

import com.entry.dexam.domain.evaluation.dto.EvaluationDto;
import com.entry.dexam.domain.evaluation.service.ExamService;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping("/{examId}")
    public ApiResponse<EvaluationDto> readExam(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @PathVariable Long examId
    ){
        EvaluationDto response = examService.readExam(userId, examId);
        return ApiResponse.ok(response);
    }
}
