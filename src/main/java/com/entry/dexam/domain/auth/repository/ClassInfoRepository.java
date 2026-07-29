package com.entry.dexam.domain.auth.repository;

import com.entry.dexam.domain.auth.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {
}