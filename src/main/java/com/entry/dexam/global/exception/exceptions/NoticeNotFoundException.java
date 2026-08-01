package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class NoticeNotFoundException extends BusinessException {
    public static final NoticeNotFoundException EXCEPTION = new NoticeNotFoundException();
    public NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
