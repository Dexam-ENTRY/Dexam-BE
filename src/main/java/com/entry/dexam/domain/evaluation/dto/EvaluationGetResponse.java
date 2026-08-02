package com.entry.dexam.domain.evaluation.dto;

import java.util.List;

public record EvaluationGetResponse(
    List<EvaluationDto> items
) {
}
