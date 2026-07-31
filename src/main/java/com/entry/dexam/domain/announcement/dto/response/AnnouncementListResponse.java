package com.entry.dexam.domain.announcement.dto.response;

import com.entry.dexam.domain.announcement.entity.Announcement;

import java.util.List;

public record AnnouncementListResponse(
        List<AnnouncementDetailResponse> items
) {
    public static AnnouncementListResponse from(List<Announcement> announcements){
        List<AnnouncementDetailResponse> items = announcements.stream()
                .map(AnnouncementDetailResponse::from)
                .toList();

        return new AnnouncementListResponse(items);
    }
}
