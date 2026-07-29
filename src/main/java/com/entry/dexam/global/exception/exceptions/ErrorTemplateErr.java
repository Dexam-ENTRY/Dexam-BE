package com.entry.dexam.global.exception.exceptions;

import com.entry.dexam.global.exception.BusinessException;
import com.entry.dexam.global.exception.ErrorCode;

public class ErrorTemplateErr extends BusinessException {
	public static final ErrorTemplateErr EXCEPTION = new ErrorTemplateErr();
    public ErrorTemplateErr() {
        super(ErrorCode.ERR_TEMPLATE);
    }
}
