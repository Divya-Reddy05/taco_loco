package com.taco.loco.baseframework.exception;

public class HttpException extends RuntimeException {
    public final int status;
    public final String incidentId;
    public final int errorCode;
    public final String description;

    public HttpException(int status, String incidentId, int errorCode, String description, Throwable cause) {
        super(description, cause);
        this.status = status;
        this.incidentId = incidentId;
        this.description = description;
        this.errorCode = errorCode;
    }

}
