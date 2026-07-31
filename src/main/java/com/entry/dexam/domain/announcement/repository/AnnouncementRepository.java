package com.entry.dexam.domain.announcement.repository;

import com.entry.dexam.domain.announcement.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
