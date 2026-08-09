package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class NoticeWriteForbiddenException extends BusinessException {
    public static final NoticeWriteForbiddenException EXCEPTION = new NoticeWriteForbiddenException();
    public NoticeWriteForbiddenException() {
        super(ErrorCode.NOTICE_WRITE_FORBIDDEN);
    }
}
