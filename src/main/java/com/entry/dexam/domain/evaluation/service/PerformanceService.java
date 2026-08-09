package com.entry.dexam.domain.evaluation.service;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.evaluation.dto.EvaluationDto;
import com.entry.dexam.domain.evaluation.dto.EvaluationGetResponse;
import com.entry.dexam.domain.evaluation.entity.Evaluation;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.entry.dexam.domain.evaluation.repository.EvaluationRepository;
import com.entry.dexam.global.exception.exceptions.PerformanceAccessDeniedException;
import com.entry.dexam.global.exception.exceptions.PerformanceNotFoundException;
import com.entry.dexam.global.exception.exceptions.UnauthorizedException;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerformanceService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    public EvaluationDto readPerformance(Long userId, Long performanceId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Evaluation evaluation = evaluationRepository.findByIdAndType(
                performanceId, EvaluationType.PERFORMANCE)
                .orElseThrow(() -> PerformanceNotFoundException.EXCEPTION);

        validateReadPermission(user, evaluation);

        return EvaluationDto.convertDto(evaluation);
    }

    public EvaluationGetResponse readPerformanceList(Long userId, String keyword){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ClassInfo classInfo = user.getClassInfo();

        if (classInfo == null) throw UnauthorizedException.EXCEPTION;

        String normalizedKeyword = keyword == null || keyword.isBlank() ? null : keyword.trim();

        List<Evaluation> evaluations = evaluationRepository
                .searchEvaluations(classInfo.getClassId().getGrade(),
                        classInfo.getClassId().getClassNum(),
                        EvaluationType.PERFORMANCE,
                        normalizedKeyword
                );

        return EvaluationGetResponse.from(evaluations);

    }

    private void validateReadPermission(User user, Evaluation evaluation){
        if(user.getClassInfo() == null ||
                !user.getClassInfo().getClassId()
                        .equals(evaluation.getClassInfo().getClassId())
        ) throw PerformanceAccessDeniedException.EXCEPTION;
    }
}
