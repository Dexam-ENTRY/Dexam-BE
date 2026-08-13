package com.entry.dexam.domain.schedule.repository;

import com.entry.dexam.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE " +
            "(:startDate IS NULL OR s.date >= :startDate) AND " +
            "(:endDate IS NULL OR s.date <= :endDate) AND " +
            "(:grade IS NULL OR s.targetGrade IS NULL OR s.targetGrade = :grade) AND " +
            "(:classNo IS NULL OR s.targetGrade IS NULL OR s.targetClassNo = :classNo) " +
            "ORDER BY s.date ASC")
    List<Schedule> searchAdminSchedules(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("grade") Integer grade,
            @Param("classNo") Integer classNo
    );
    @Query("SELECT s FROM Schedule s WHERE " +
            "(:startDate IS NULL OR s.date >= :startDate) AND " +
            "(:endDate IS NULL OR s.date <= :endDate) AND " +
            "(s.targetGrade IS NULL OR s.targetGrade = :grade) AND " +
            "(s.targetClassNo IS NULL OR s.targetClassNo = :classNo) " +
            "ORDER BY s.date ASC")
    List<Schedule> searchSchedules(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("grade") Integer grade,
            @Param("classNo") Integer classNo
    );
}