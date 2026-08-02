package com.entry.dexam.domain.evaluation.dto;

import com.entry.dexam.domain.auth.dto.ClassInfoDto;
import com.entry.dexam.domain.evaluation.entity.Evaluation;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;

import java.time.LocalDate;

public record EvaluationDto(
        Long id,
        EvaluationType type,
        String title,
        ClassInfoDto target,
        String content,
        LocalDate date
) {
    public static EvaluationDto convertDto(Evaluation evaluation) {
        return new EvaluationDto(
                evaluation.getId(),
                evaluation.getType(),
                evaluation.getTitle(),
                new ClassInfoDto(
                    evaluation.getClassInfo().getClassId().getGrade(),
                    evaluation.getClassInfo().getClassId().getClassNum()
                ),
                evaluation.getContent(),
                evaluation.getDate()
        );
    }
}
