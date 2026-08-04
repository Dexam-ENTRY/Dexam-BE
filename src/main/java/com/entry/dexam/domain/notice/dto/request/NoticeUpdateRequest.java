package com.entry.dexam.domain.notice.dto.request;

import com.entry.dexam.domain.notice.enums.Target;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record NoticeUpdateRequest(

        @Size(max = 255, message = "제목은 255자 이하로 입력해주세요.")
        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @Size(max = 255, message = "내용은 255자 이하로 입력해주세요.")
        @NotBlank(message = "내용을 입력해주세요.")
        String content,

        @NotNull(message = "공지 대상을 선택해주세요.")
        Target target,

        @PositiveOrZero
        int grade,

        @PositiveOrZero
        int classNo
) {
}
