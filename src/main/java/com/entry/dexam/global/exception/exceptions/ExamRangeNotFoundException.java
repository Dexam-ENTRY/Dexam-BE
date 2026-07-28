package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ExamRangeNotFoundException extends BusinessException {
	public static final ExamRangeNotFoundException EXCEPTION = new ExamRangeNotFoundException();
    public ExamRangeNotFoundException() {
        super(ErrorCode.EXAM_RANGE_NOT_FOUND);
    }
}