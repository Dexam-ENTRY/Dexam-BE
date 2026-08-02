package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ClassNotFoundException extends BusinessException {
    public static final ClassNotFoundException EXCEPTION = new ClassNotFoundException();
    public ClassNotFoundException() {
        super(ErrorCode.CLASS_NOT_FOUND);
    }
}