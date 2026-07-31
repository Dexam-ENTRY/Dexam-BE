package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class AdminNotChangeClassException extends BusinessException {
    public static final AdminNotChangeClassException EXCEPTION = new AdminNotChangeClassException();
    public AdminNotChangeClassException() {
        super(ErrorCode.ADMIN_NOT_CHANGE_CLASS);
    }
}
