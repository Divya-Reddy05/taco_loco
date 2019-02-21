package com.taco.loco.baseframework.exception;

import com.taco.loco.baseframework.exception.enums.ApiErrors;

public class SystemException extends BaseException {
    public SystemException(ApiErrors apiErrorsEnum) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), apiErrorsEnum.getErrorDescription());
    }

}
