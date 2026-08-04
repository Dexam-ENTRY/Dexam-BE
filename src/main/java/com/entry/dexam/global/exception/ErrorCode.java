package com.entry.dexam.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	ERR_TEMPLATE(200, "ERR_TEMPLATE", "에러 템플릿입니다."),

	NOT_VALID_DTO_ERR(400, "NOT_VALID_DTO_ERR", "유효하지 않은 요청입니다."),
	VALIDATION_FAILED(400, "VALIDATION_FAILED", "요청 값이 올바르지 않습니다."),

	UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),

	FORBIDDEN(403, "FORBIDDEN", "권한이 없습니다."),
	NOTICE_WRITE_FORBIDDEN(403, "NOTICE_WRITE_FORBIDDEN", "공지 작성/수정 권한이 없습니다."),
	ADMIN_NOT_CHANGE_CLASS(403, "ADMIN_NOT_CHANGE_CLASS", "반 관리자는 반 수정이 불가능합니다. 관리자에게 문의하여 주세요."),

	NOT_FOUND(404, "NOT_FOUND", "리소스를 찾을 수 없습니다."),
	EXAM_RANGE_NOT_FOUND(404, "EXAM_RANGE_NOT_FOUND", "출제 범위를 찾을 수 없습니다."),
	USER_NOT_FOUND(404, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
	CLASS_NOT_FOUND(404, "CLASS_NOT_FOUND", "반을 찾을 수 없습니다."),
	TOKEN_NOT_FOUND(404, "TOKEN_NOT_FOUND", "토큰을 찾을 수 없습니다."),
	NOTICE_NOT_FOUND(404, "NOTICE_NOT_FOUND", "공지를 찾을 수 없습니다."),
	EVALUATION_NOT_FOUND(404, "EVALUATION_NOT_FOUND", "평가를 찾을 수 없습니다."),

	INTERNAL_SERVER_ERR(500, "INTERNAL_SERVER_ERR", "서버 측 오류가 발생했습니다.");
	
	private Integer statusCode;
	private String errorCode;
    private String errorMessage;
}
