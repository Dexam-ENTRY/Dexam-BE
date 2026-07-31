package com.entry.dexam.domain.notice.dto.response;

import com.entry.dexam.domain.notice.entity.Notice;

public record NoticeUpdateResponse(
        Long id,
        String message
) {

    public static NoticeUpdateResponse from(Notice notice){
        return new NoticeUpdateResponse(
                notice.getId(),
                "공지사항이 수정되었습니다."
                );
    }
}
