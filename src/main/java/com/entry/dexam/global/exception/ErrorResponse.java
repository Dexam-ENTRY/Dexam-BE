package com.entry.dexam.global.exception;

public record ErrorResponse(
	boolean success,
    Object error
) {
	public static ErrorResponse errorCodeFrom(ErrorCode errorCode) {
        return new ErrorResponse(
                false,
                errorCode.getErrorMessage()
        );
    }
	
	public static ErrorResponse errorCodeFrom(ErrorCode errorCode, String description) {
        return new ErrorResponse(
                false,
                errorCode.getErrorMessage() + description
        );
    }
	
	public static ErrorResponse dtoErrorCodeFrom(Object mapObject) {
        return new ErrorResponse(
                false,
                mapObject
        );
    }

    public static ErrorResponse errorCodeOf(String errorMsg) {
        return new ErrorResponse(
                false,
                errorMsg
        );
    }
}
