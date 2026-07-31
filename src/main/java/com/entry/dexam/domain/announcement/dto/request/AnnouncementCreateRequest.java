package com.entry.dexam.domain.announcement.dto.request;

import com.entry.dexam.domain.announcement.enums.Target;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnnouncementCreateRequest(

        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        String content,

        @NotNull(message = "공지 대상을 선택해주세요.")
        Target target,

        Integer grade,
        Integer classNo
) {
}
