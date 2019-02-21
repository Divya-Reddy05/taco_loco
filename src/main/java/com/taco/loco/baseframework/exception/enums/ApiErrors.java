package com.taco.loco.baseframework.exception.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.TreeMap;

public enum ApiErrors {

    BLOCKED_PROTOCOL(HttpStatus.UPGRADE_REQUIRED.value(), 1000, "Protocol Upgrade required"),

    MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES(HttpStatus.BAD_REQUEST.value(), 4000, "Missing mandatory data"),
    INVALID_ITEM(HttpStatus.BAD_REQUEST.value(), 4005, "Invalid Item in the request"),
    INVALID_REQUEST(HttpStatus.NOT_ACCEPTABLE.value(), 4008, "Invalid request"),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), 5405, "Method Not Allowed"),

    INTERNAL_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 7004, "Internal processing error");

    public static final Map<String, ApiErrors> LOOKUP_BY_ERROR_DESCRIPTION = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    public static final Map<Integer, ApiErrors> LOOKUP_BY_ERROR_CODE = new TreeMap<>();

    static {
        for (ApiErrors s : values()) {
            LOOKUP_BY_ERROR_DESCRIPTION.put(s.getErrorDescription(), s);
            LOOKUP_BY_ERROR_CODE.put(s.getErrorCode(), s);
        }
    }

    private final Integer httpStatus;
    private final Integer errorCode;
    private final String errorDescription;

    private ApiErrors(Integer httpStatus, Integer errorCode, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
