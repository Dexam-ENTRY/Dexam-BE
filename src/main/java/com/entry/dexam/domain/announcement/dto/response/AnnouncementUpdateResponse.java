package com.entry.dexam.domain.announcement.dto.response;

import com.entry.dexam.domain.announcement.entity.Announcement;

public record AnnouncementUpdateResponse(
        Long id,
        String message
) {

    public static AnnouncementUpdateResponse from(Announcement announcement){
        return new AnnouncementUpdateResponse(
                announcement.getId(),
                "공지사항이 수정되었습니다."
                );
    }
}
