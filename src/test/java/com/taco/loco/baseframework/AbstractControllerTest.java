package com.taco.loco.baseframework;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ControllerTestConfiguration.class)
@WebAppConfiguration
public abstract class AbstractControllerTest<T> {

    protected MockMvc mockMvc;
    protected T controller;

    @Autowired
    private HttpMessageConverter<?>[] httpMessageConverters;

    @Autowired
    private FormattingConversionService conversionService;

    protected abstract T createController();

    protected void configureMockMvc(StandaloneMockMvcBuilder mvcBuilder) {
        // can be overridden by subclasses
    }

    @Before
    public void setupMockMvc() {
        controller = createController();

        StandaloneMockMvcBuilder mvcBuilder = MockMvcBuilders.standaloneSetup(createController()).setMessageConverters
                (httpMessageConverters).setConversionService(conversionService);

        configureMockMvc(mvcBuilder);
        mockMvc = mvcBuilder.build();
    }

    /**
     * Helper to be used in conjunction with the Spring MVC assertions. For example: mockMvc.perform(get("/"))
     * .andExpect(jsonBody("{ \"foo\": \"bar\" }));
     */
    protected ResultMatcher jsonBody(final String expectedJson) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), true);
            }
        };
    }

    protected MockHttpServletRequestBuilder getWithoutBody(String uri) {
        return get(uri).contentType(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder postWithBody(String uri, String body) {
        return post(uri).content(body).contentType(MediaType.APPLICATION_JSON);
    }

    protected ResultMatcher contentType(final String expectedContentType) {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                content().contentType(expectedContentType);
            }
        };
    }

}