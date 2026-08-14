package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ScheduleNotFoundException extends BusinessException {
	public static final ScheduleNotFoundException EXCEPTION = new ScheduleNotFoundException();
    public ScheduleNotFoundException() {
        super(ErrorCode.SCHEDULE_NOT_FOUND);
    }
}
