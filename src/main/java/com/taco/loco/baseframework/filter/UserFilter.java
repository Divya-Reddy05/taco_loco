package com.taco.loco.baseframework.filter;

import com.google.common.base.Strings;
import com.taco.loco.baseframework.response.wrapper.HeaderMapRequestWrapper;
import com.taco.loco.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter extends OncePerRequestFilter {

    /*
     * Default filter method for overriding
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);

        /**
         * For Cross Domain requests
         */
        if (!Strings.isNullOrEmpty(request.getHeader(Constants.KEY_ORIGIN))) {
            response.addHeader("Access-Control-Allow-Origin", request.getHeader(Constants.KEY_ORIGIN));
        }

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        response.setHeader("supportsCredentials", "true");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        /**
         * Checking if Accept and Content Type Headers are provided
         */
        if (!Strings.isNullOrEmpty(requestWrapper.getHeader(Constants.KEY_ACCEPT))) {
            requestWrapper.addHeader(Constants.KEY_ACCEPT, Constants.VALUE_CONTENT_TYPE_JSON);
        }

        if (!Strings.isNullOrEmpty(requestWrapper.getHeader(Constants.KEY_CONTENT_TYPE))) {
            requestWrapper.addHeader(Constants.KEY_CONTENT_TYPE, Constants.VALUE_CONTENT_TYPE_JSON);
        }

        filterChain.doFilter(requestWrapper, response);
        response.flushBuffer();
    }
}