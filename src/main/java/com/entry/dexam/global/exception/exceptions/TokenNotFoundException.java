package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class TokenNotFoundException extends BusinessException {
    public static final TokenNotFoundException EXCEPTION = new TokenNotFoundException();
    public TokenNotFoundException() {
        super(ErrorCode.TOKEN_NOT_FOUND);
    }
}
