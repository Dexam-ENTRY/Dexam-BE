package com.entry.dexam.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	ERR_TEMPLATE(200, "에러 템플릿입니다."),
	EXAM_RANGE_NOT_FOUND(404, "출제 범위를 찾을 수 없습니다."),

	NOT_VALID_DTO_ERR(400, "유효하지 않은 요청입니다."),
		
	INTERNAL_SERVER_ERR(500, "서버 측 오류가 발생했습니다.");
	
	private Integer statusCode;
    private String errorMessage;
}
