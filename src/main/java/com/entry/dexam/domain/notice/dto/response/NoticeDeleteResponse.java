package com.entry.dexam.domain.notice.dto.response;

public record NoticeDeleteResponse(
        String message
) {
    public static NoticeDeleteResponse of() {
        return new NoticeDeleteResponse("공지사항이 삭제되었습니다.");
    }
}
