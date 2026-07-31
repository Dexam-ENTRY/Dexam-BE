package com.entry.dexam.domain.announcement.dto.response;

import com.entry.dexam.domain.announcement.entity.Announcement;

public record AnnouncementDeleteResponse(
        String message
) {
    public static AnnouncementDeleteResponse of() {
        return new AnnouncementDeleteResponse("공지사항이 삭제되었습니다.");
    }
}
