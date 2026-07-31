package com.entry.dexam.domain.notice.repository;

import com.entry.dexam.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
