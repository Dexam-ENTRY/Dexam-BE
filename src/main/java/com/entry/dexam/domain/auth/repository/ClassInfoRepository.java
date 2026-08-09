package com.entry.dexam.domain.auth.repository;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import com.entry.dexam.domain.auth.entity.ClassId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassInfoRepository extends JpaRepository<ClassInfo, ClassId> {

    Optional<ClassInfo> findByClassIdGradeAndClassIdClassNum(int grade, int classNo);
}