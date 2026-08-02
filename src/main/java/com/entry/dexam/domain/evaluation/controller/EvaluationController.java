package com.entry.dexam.domain.evaluation.controller;

import com.entry.dexam.domain.evaluation.dto.*;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.entry.dexam.domain.evaluation.service.EvaluationService;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/api/admin/evaluation")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping
    public ApiResponse<EvaluationGetResponse> getEvaluations(
        @RequestParam(name = "type", required = false) EvaluationType type,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth date,
        @Parameter(hidden = true) @CurrentUserId Long id
    ) {
        EvaluationGetResponse data = evaluationService.getEvaluations(type, id, date);
        return ApiResponse.ok(data);
    }

    @PostMapping
    public ApiResponse<EvaluationIdResponse> addEvaluation(
        @RequestBody @Valid EvaluationAddRequest evaluationAddRequest,
        @Parameter(hidden = true) @CurrentUserId Long id
    ) {
        EvaluationIdResponse evaluationIdResponse = evaluationService.addEvaluation(evaluationAddRequest, id);
        return ApiResponse.ok(evaluationIdResponse);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteEvaluation(
            @PathVariable Long id,
            @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        evaluationService.deleteEvaluation(userId, id);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}")
    public ApiResponse<EvaluationIdResponse> patchEvaluation(
        @PathVariable Long id,
        @RequestBody @Valid EvaluationPatchRequest evaluationPatchRequest,
        @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        EvaluationIdResponse evaluationIdResponse = evaluationService.patchEvaluation(evaluationPatchRequest, userId, id);
        return ApiResponse.ok(evaluationIdResponse);
    }
}
