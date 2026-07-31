package com.entry.dexam.domain.evaluation.service;

import com.entry.dexam.domain.auth.entity.ClassId;
import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.evaluation.dto.*;
import com.entry.dexam.domain.evaluation.entity.Evaluation;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import com.entry.dexam.domain.evaluation.repository.EvaluationRepository;
import com.entry.dexam.global.exception.exceptions.EvaluationNotFoundException;
import com.entry.dexam.global.exception.exceptions.ForbiddenException;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    public boolean checkClassPermission(User user, int grade, int classNum) {
        ClassInfo classInfo = user.getClassInfo();
        if (classInfo == null) {
            return false;
        }

        ClassId classId = classInfo.getClassId();
        return classId.getGrade() == grade && classId.getClassNum() == classNum;
    }

    @Transactional
    public EvaluationGetResponse getEvaluations(EvaluationType type, String email, YearMonth date) {
        User user = userRepository.findByEmailWithClassInfo(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ClassInfo classInfo = user.getClassInfo();
        if (classInfo == null) {
            throw ForbiddenException.EXCEPTION;
        }

        LocalDate start = date.atDay(1);
        LocalDate end = date.plusMonths(1).atDay(1);

        int grade = user.getClassInfo().getClassId().getGrade();
        int classNum = user.getClassInfo().getClassId().getClassNum();

        List<Evaluation> evaluations = evaluationRepository.findEvaluations(
                grade,
                classNum,
                start,
                end,
                type
        );

        List<EvaluationDto> items = evaluations.stream()
                .map(EvaluationDto::convertDto)
                .toList();

        return new EvaluationGetResponse(items);
    }

    @Transactional
    public EvaluationIdResponse addEvaluation(EvaluationAddRequest dto, String email) {
        User user = userRepository.findByEmailWithClassInfo(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        int grade = dto.target().grade();
        int classNum = dto.target().classNum();

        if (!checkClassPermission(user, grade, classNum)) {
            throw ForbiddenException.EXCEPTION;
        }

        ClassInfo classInfo = user.getClassInfo();

        Evaluation evaluation = Evaluation.builder()
                .author(user)
                .classInfo(classInfo)
                .title(dto.title())
                .content(dto.content())
                .date(dto.date())
                .type(dto.type())
                .lastModifiedUser(null)
                .build();

        evaluationRepository.save(evaluation);

        return new EvaluationIdResponse(evaluation.getId());
    }

    @Transactional
    public EvaluationIdResponse patchEvaluation(EvaluationPatchRequest dto, String email, Long id) {
        User user = userRepository.findByEmailWithClassInfo(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> EvaluationNotFoundException.EXCEPTION);

        int grade = evaluation.getClassInfo().getClassId().getGrade();
        int classNum = evaluation.getClassInfo().getClassId().getClassNum();

        if (!checkClassPermission(user, grade, classNum)) {
            throw ForbiddenException.EXCEPTION;
        }

        evaluation.update(dto, user);
        evaluationRepository.save(evaluation);

        return new EvaluationIdResponse(evaluation.getId());
    }

    @Transactional
    public void deleteEvaluation(String email, Long id) {
        User user = userRepository.findByEmailWithClassInfo(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> EvaluationNotFoundException.EXCEPTION);

        int grade = evaluation.getClassInfo().getClassId().getGrade();
        int classNum = evaluation.getClassInfo().getClassId().getClassNum();

        if (!checkClassPermission(user, grade, classNum)) {
            throw ForbiddenException.EXCEPTION;
        }

        evaluationRepository.delete(evaluation);
    }
}
