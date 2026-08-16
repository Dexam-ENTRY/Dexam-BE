package com.entry.dexam.domain.evaluation.service;


import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.evaluation.dto.EvaluationDto;
import com.entry.dexam.domain.evaluation.dto.EvaluationGetResponse;
import com.entry.dexam.domain.evaluation.entity.Evaluation;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.entry.dexam.domain.evaluation.repository.EvaluationRepository;
import com.entry.dexam.global.exception.exceptions.ExamAccessDeniedException;
import com.entry.dexam.global.exception.exceptions.ExamNotFoundException;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExamService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    public EvaluationDto readExam(Long userId, Long examId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Evaluation evaluation = evaluationRepository.findByIdAndType(examId, EvaluationType.EXAM)
                .orElseThrow(() -> ExamNotFoundException.EXCEPTION);

        if(user.getClassInfo() == null || !user.getClassInfo().getClassId().equals(evaluation.getClassInfo().getClassId())) throw ExamAccessDeniedException.EXCEPTION;

        return EvaluationDto.convertDto(evaluation);
    }
    public EvaluationGetResponse readExamList(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ClassInfo classInfo = user.getClassInfo();
        if (classInfo == null) throw ExamAccessDeniedException.EXCEPTION;

        List<Evaluation> exams = evaluationRepository.searchExams(
                classInfo.getClassId().getGrade(),
                classInfo.getClassId().getClassNum(),
                startDate,
                endDate,
                EvaluationType.EXAM
        );

        return EvaluationGetResponse.from(exams);
    }
}
