package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ExamAccessDeniedException extends BusinessException {
	public static final ExamAccessDeniedException EXCEPTION = new ExamAccessDeniedException();
    public ExamAccessDeniedException() {
        super(ErrorCode.EXAM_ACCESS_DENIED);
    }
}
