package com.taco.loco.baseframework.exception;

import com.taco.loco.baseframework.exception.enums.ApiErrors;

public class DataFormatException extends BaseException {
    public DataFormatException(ApiErrors apiErrorsEnum) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), apiErrorsEnum.getErrorDescription());
    }
}