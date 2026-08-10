package com.entry.dexam.domain.evaluation.dto;

import com.entry.dexam.domain.evaluation.entity.Evaluation;

import java.util.List;

public record EvaluationGetResponse(
    List<EvaluationDto> items
) {
    public static EvaluationGetResponse from(List<Evaluation> evaluations) {
        List<EvaluationDto> items = evaluations.stream()
                .map(EvaluationDto::convertDto)
                .toList();

        return new EvaluationGetResponse(items);
    }
}