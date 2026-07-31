package com.entry.dexam.domain.announcement.dto.response;

import com.entry.dexam.domain.announcement.entity.Announcement;
import com.entry.dexam.domain.announcement.enums.Target;

import java.time.LocalDateTime;

public record AnnouncementDetailResponse(
        Long id,
        String title,
        String content,
        Target target,
        LocalDateTime createdAt
) {
    public static AnnouncementDetailResponse from(Announcement announcement){
        return new AnnouncementDetailResponse(
                announcement.getId(),
                announcement.getTitle(),
                announcement.getContent(),
                announcement.getTarget(),
                announcement.getCreatedAt()
        );
    }
}
