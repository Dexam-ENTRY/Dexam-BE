package com.entry.dexam.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	ERR_TEMPLATE(200, "ERR_TEMPLATE", "에러 템플릿입니다."),

	NOT_VALID_DTO_ERR(400, "NOT_VALID_DTO_ERR", "유효하지 않은 요청입니다."),

	UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),

	FORBIDDEN(403, "FORBIDDEN", "권한이 없습니다"),

	EXAM_RANGE_NOT_FOUND(404, "EXAM_RANGE_NOT_FOUND", "출제 범위를 찾을 수 없습니다."),
	USER_NOT_FOUND(404, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
	CLASS_NOT_FOUND(404, "CLASS_NOT_FOUND", "반을 찾을 수 없습니다."),
		
	INTERNAL_SERVER_ERR(500, "INTERNAL_SERVER_ERR", "서버 측 오류가 발생했습니다.");
	
	private Integer statusCode;
	private String errorCode;
    private String errorMessage;
}
