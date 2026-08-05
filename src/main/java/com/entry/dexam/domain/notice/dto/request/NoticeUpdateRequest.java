package com.entry.dexam.domain.notice.dto.request;

import com.entry.dexam.domain.notice.enums.Target;
import jakarta.validation.constraints.*;

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
        @AssertTrue(message = "학급 공지는 학년과 반을 선택해주세요.")
        public boolean isClassInfoValid(){
                return target != Target.CLASS || (grade >= 1 && classNo >= 1);
        }
}
