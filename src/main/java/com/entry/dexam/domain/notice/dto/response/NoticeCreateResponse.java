package com.entry.dexam.domain.notice.dto.response;

import com.entry.dexam.domain.notice.entity.Notice;

public record NoticeCreateResponse(
        Long id,
        String message
) {

    public static NoticeCreateResponse from(Notice notice){
        return new NoticeCreateResponse(
                notice.getId(),
                "공지사항이 작성되었습니다."
        );
    }
}
