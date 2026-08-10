package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class PerformanceAccessDeniedException extends BusinessException {
    public static final PerformanceAccessDeniedException EXCEPTION = new PerformanceAccessDeniedException();
    public PerformanceAccessDeniedException() {
        super(ErrorCode.PERFORMANCE_ACCESS_DENIED);
    }
}
