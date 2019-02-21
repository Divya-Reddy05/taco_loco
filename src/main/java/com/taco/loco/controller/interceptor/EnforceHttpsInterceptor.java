package com.taco.loco.controller.interceptor;

import com.taco.loco.baseframework.exception.HttpException;
import com.taco.loco.baseframework.exception.enums.ApiErrors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class EnforceHttpsInterceptor implements PreHandlerInterceptor {

    private static final String PROTO_HEADER_NAME = "x-forwarded-proto";

    private static final String HTTPS_PROTOCOL = "https";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String forwardedProtocol = request.getHeader(PROTO_HEADER_NAME);

        if (forwardedProtocol == null || isHttps(forwardedProtocol))
            return true;

        throw new HttpException(ApiErrors.BLOCKED_PROTOCOL.getHttpStatus(), UUID.randomUUID().toString(),
                ApiErrors.BLOCKED_PROTOCOL.getErrorCode(), ApiErrors.BLOCKED_PROTOCOL.getErrorDescription(), new Throwable());
    }

    private boolean isHttps(String forwardedProtocol) {
        return forwardedProtocol.equalsIgnoreCase(HTTPS_PROTOCOL);
    }

}
