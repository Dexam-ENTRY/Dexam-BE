package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ForbiddenException extends BusinessException {
    public static final ForbiddenException EXCEPTION = new ForbiddenException();
    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
