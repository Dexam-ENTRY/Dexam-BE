package com.entry.dexam.global.exception;

public record ErrorResponse(
	Boolean success,
    Object error
) {
	public static ErrorResponse errorCodeFrom(ErrorCode errorCode) {
        return new ErrorResponse(
                Boolean.FALSE,
                errorCode.getErrorMessage()
        );
    }
	
	public static ErrorResponse errorCodeFrom(ErrorCode errorCode, String description) {
        return new ErrorResponse(
                Boolean.FALSE,
                errorCode.getErrorMessage() + description
        );
    }
	
	public static ErrorResponse dtoErrorCodeFrom(Object mapObject) {
        return new ErrorResponse(
                Boolean.FALSE,
                mapObject
        );
    }

    public static ErrorResponse errorCodeOf(String errorMsg) {
        return new ErrorResponse(
                Boolean.FALSE,
                errorMsg
        );
    }
}
