package com.entry.dexam.domain.evaluation.controller;

import com.entry.dexam.domain.evaluation.dto.EvaluationAddRequest;
import com.entry.dexam.domain.evaluation.dto.EvaluationIdResponse;
import com.entry.dexam.domain.evaluation.dto.EvaluationPatchRequest;
import com.entry.dexam.domain.evaluation.service.EvaluationService;
import com.entry.dexam.global.anotations.CurrentUserEmail.CurrentUserEmail;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluation")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping
    public ApiResponse<EvaluationIdResponse> addEvaluation(
        @RequestBody @Valid EvaluationAddRequest evaluationAddRequest,
        @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        EvaluationIdResponse evaluationIdResponse = evaluationService.addEvaluation(evaluationAddRequest, email);
        return ApiResponse.ok(evaluationIdResponse);
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
