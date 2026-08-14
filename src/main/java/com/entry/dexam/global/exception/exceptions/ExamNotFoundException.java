package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ExamNotFoundException extends BusinessException {
	public static final ExamNotFoundException EXCEPTION = new ExamNotFoundException();
    public ExamNotFoundException() {
        super(ErrorCode.EXAM_NOT_FOUND);
    }
}
