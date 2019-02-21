package com.taco.loco.baseframework.exception;

import com.taco.loco.baseframework.exception.enums.ApiErrors;

public class BusinessException extends BaseException {

    public BusinessException(ApiErrors apiErrorsEnum) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), apiErrorsEnum.getErrorDescription());
    }

    public BusinessException(ApiErrors apiErrorsEnum, String errorDescription) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), errorDescription);
    }
}
