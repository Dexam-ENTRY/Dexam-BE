package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ValidationFailedException extends BusinessException {
  public static final ValidationFailedException EXCEPTION = new ValidationFailedException();
    public ValidationFailedException() {
        super(ErrorCode.VALIDATION_FAILED);
    }
}
