package com.taco.loco.baseframework.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.taco.loco.baseframework.exception.*;
import com.taco.loco.baseframework.exception.enums.ApiErrors;
import com.taco.loco.baseframework.response.HttpExceptionJson;
import com.taco.loco.baseframework.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private ResponseEntity<HttpExceptionJson> handleHttpException(HttpException httpException) {
        HttpExceptionJson httpExceptionJson = new HttpExceptionJson();
        httpExceptionJson.setIncidentId(httpException.incidentId);
        httpExceptionJson.setErrorCode(httpException.errorCode);
        httpExceptionJson.setErrorDescription(httpException.description);
        return new ResponseEntity<>(httpExceptionJson, HttpStatus.valueOf(httpException.status));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<HttpExceptionJson> handleBaseException(BaseException e) {
        return handleHttpException(new HttpException(e.getHttpStatus(), e.getIncidentID(), e.getErrorCode(), e.getErrorDescription
                (), e));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<HttpExceptionJson> handleBusinessException(BusinessException e) {
        return handleHttpException(new HttpException(e.getHttpStatus(), e.getIncidentID(), e.getErrorCode(), e.getErrorDescription(), e));
    }

    @ExceptionHandler(DataFormatException.class)
    public ResponseEntity<HttpExceptionJson> handleDataFormatException(DataFormatException e) {
        return handleHttpException(new HttpException(e.getHttpStatus(), e.getIncidentID(), e.getErrorCode(), e.getErrorDescription(), e));
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<HttpExceptionJson> handleSystemException(SystemException e) {
        return handleHttpException(new HttpException(e.getHttpStatus(), e.getIncidentID(), e.getErrorCode(), e.getErrorDescription(), e));
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    @ResponseBody
    public ResponseEntity<Response> exceptionHandlerForInvalidJSONStructure(Exception ex,
                                                                            HttpServletResponse httpServletResponse) {
        Response response = new Response();
        httpServletResponse.setStatus(ApiErrors.INVALID_REQUEST.getHttpStatus());
        StringBuilder errorDesc = new StringBuilder("Invalid request");
        UnrecognizedPropertyException exception = (UnrecognizedPropertyException) ex;
        errorDesc.append(" - [").append(exception.getPropertyName()).append("]");
        response.setError(ApiErrors.INVALID_REQUEST.getErrorCode(), errorDesc.toString());
        response.setIncidentID(UUID.randomUUID().toString());
        LOGGER.error("ERROR - Error occurred with incident id: {} {}", ex, UUID.randomUUID().toString());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ApiErrors.INVALID_REQUEST.getHttpStatus()));
    }

    @ExceptionHandler(NotReadablePropertyException.class)
    @ResponseBody
    public ResponseEntity<Response> exceptionHandlerForInvalidJSON(Exception ex,
                                                                   HttpServletResponse httpServletResponse) {
        Response response = new Response();
        httpServletResponse.setStatus(ApiErrors.INVALID_REQUEST.getHttpStatus());
        StringBuilder errorDesc = new StringBuilder("Invalid request");
        NotReadablePropertyException exception = (NotReadablePropertyException) ex;
        errorDesc = errorDesc.append(" - [").append(exception.getPropertyName()).append("]");
        response.setError(ApiErrors.INVALID_REQUEST.getErrorCode(), errorDesc.toString());
        response.setIncidentID(UUID.randomUUID().toString());
        LOGGER.error("ERROR - Error occurred with incident id: {} {}", ex, UUID.randomUUID().toString());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ApiErrors.INVALID_REQUEST.getHttpStatus()));
    }
}