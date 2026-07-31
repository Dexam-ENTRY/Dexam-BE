package com.entry.dexam.domain.announcement.dto.response;

import com.entry.dexam.domain.announcement.entity.Announcement;

public record AnnouncementCreateResponse(
        Long id,
        String message
) {

    public static AnnouncementCreateResponse from(Announcement announcement){
        return new AnnouncementCreateResponse(
                announcement.getId(),
                "공지사항이 작성되었습니다."
        );
    }
}
