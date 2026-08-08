package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class PerformanceNotFoundException extends BusinessException {
    public static final PerformanceNotFoundException EXCEPTION = new PerformanceNotFoundException();
    public PerformanceNotFoundException() {
        super(ErrorCode.PERFORMANCE_NOT_FOUND);
    }
}
