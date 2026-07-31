package com.entry.dexam.domain.evaluation.repository;

import com.entry.dexam.domain.evaluation.entity.Evaluation;
import com.entry.dexam.domain.evaluation.enums.EvaluationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query("""
        select e
        from Evaluation e
        where e.classInfo.classId.grade = :grade
          and e.classInfo.classId.classNum = :classNum
          and e.date >= :start
          and e.date < :end
          and (:type is null or e.type = :type)
    """)
    List<Evaluation> findEvaluations(
            @Param("grade") int grade,
            @Param("classNum") int classNum,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("type") EvaluationType type
    );
}
