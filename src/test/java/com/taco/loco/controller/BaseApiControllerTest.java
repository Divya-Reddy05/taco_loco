
package com.taco.loco.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.taco.loco.baseframework.controller.BaseController;
import com.taco.loco.baseframework.exception.enums.ApiErrors;
import com.taco.loco.baseframework.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class BaseApiControllerTest {

    @InjectMocks
    BaseController baseApiController = new BaseController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExceptionHandlerForInvalidJSON() {
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        UnrecognizedPropertyException exception = Mockito.mock(UnrecognizedPropertyException.class);
        when(exception.getPropertyName()).thenReturn("unknown");
        ResponseEntity<Response> response = baseApiController.exceptionHandlerForInvalidJSONStructure(exception, httpServletResponse);
        assertEquals(ApiErrors.INVALID_REQUEST.getErrorCode(), (Integer) response.getBody().getErrorCode());
    }

    @Test
    public void testExceptionHandlerForInvalidProperty() {
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        NotReadablePropertyException exception = Mockito.mock(NotReadablePropertyException.class);
        ResponseEntity<Response> response = baseApiController.exceptionHandlerForInvalidJSON(exception, httpServletResponse);
        assertEquals((Integer) ApiErrors.INVALID_REQUEST.getErrorCode(), (Integer) response.getBody().getErrorCode());
    }

}
