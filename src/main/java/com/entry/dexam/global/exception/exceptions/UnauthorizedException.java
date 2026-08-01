package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class UnauthorizedException extends BusinessException {
    public static final UnauthorizedException EXCEPTION = new UnauthorizedException();
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
