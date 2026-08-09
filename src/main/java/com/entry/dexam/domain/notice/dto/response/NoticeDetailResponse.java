package com.entry.dexam.domain.notice.dto.response;

import com.entry.dexam.domain.notice.entity.Notice;
import com.entry.dexam.domain.notice.enums.Target;

import java.time.LocalDateTime;

public record NoticeDetailResponse(
        Long id,
        String title,
        String content,
        Target target,
        LocalDateTime createdAt
) {
    public static NoticeDetailResponse from(Notice notice){
        return new NoticeDetailResponse(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getTarget(),
                notice.getCreatedAt()
        );
    }
}
