package com.entry.dexam.domain.notice.dto.response;

import com.entry.dexam.domain.notice.entity.Notice;

import java.util.List;

public record NoticeListResponse(
        List<NoticeDetailResponse> items
) {
    public static NoticeListResponse from(List<Notice> notices){
        List<NoticeDetailResponse> items = notices.stream()
                .map(NoticeDetailResponse::from)
                .toList();

        return new NoticeListResponse(items);
    }
}
