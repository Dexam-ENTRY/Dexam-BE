package com.entry.dexam.domain.evaluation.controller;

import com.entry.dexam.domain.evaluation.dto.EvaluationAddRequest;
import com.entry.dexam.domain.evaluation.dto.EvaluationGetResponse;
import com.entry.dexam.domain.evaluation.dto.EvaluationIdResponse;
import com.entry.dexam.domain.evaluation.dto.EvaluationPatchRequest;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.entry.dexam.domain.evaluation.service.EvaluationService;
import com.entry.dexam.global.anotations.CurrentUserEmail.CurrentUserEmail;
import com.entry.dexam.global.dto.ApiResponse;
import com.entry.dexam.global.exception.exceptions.ErrorTemplateErr;
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
        @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        EvaluationGetResponse data = evaluationService.getEvaluations(type, email, date);
        return ApiResponse.ok(data);
    }

    @PostMapping
    public ApiResponse<EvaluationIdResponse> addEvaluation(
        @RequestBody @Valid EvaluationAddRequest evaluationAddRequest,
        @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        EvaluationIdResponse evaluationIdResponse = evaluationService.addEvaluation(evaluationAddRequest, email);
        return ApiResponse.ok(evaluationIdResponse);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteEvaluation(
            @PathVariable Long id,
            @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        evaluationService.deleteEvaluation(email, id);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}")
    public ApiResponse<EvaluationIdResponse> patchEvaluation(
        @PathVariable Long id,
        @RequestBody @Valid EvaluationPatchRequest evaluationPatchRequest,
        @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        EvaluationIdResponse evaluationIdResponse = evaluationService.patchEvaluation(evaluationPatchRequest, email, id);
        return ApiResponse.ok(evaluationIdResponse);
    }
}
