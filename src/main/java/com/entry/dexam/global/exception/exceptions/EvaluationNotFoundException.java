package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class EvaluationNotFoundException extends BusinessException {
    public static final EvaluationNotFoundException EXCEPTION = new EvaluationNotFoundException();
    public EvaluationNotFoundException() {
        super(ErrorCode.EVALUATION_NOT_FOUND);
    }
}
