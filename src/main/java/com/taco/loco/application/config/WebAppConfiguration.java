package com.taco.loco.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.loco.baseframework.filter.UserFilter;
import com.taco.loco.controller.interceptor.CacheControlInterceptor;
import com.taco.loco.controller.interceptor.EnforceHttpsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EnforceHttpsInterceptor httpsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpsInterceptor);
        registry.addInterceptor(new CacheControlInterceptor());
    }

    @Bean
    public Filter userFilter() {
        return new UserFilter();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createJacksonConverter());
    }

    private MappingJackson2HttpMessageConverter createJacksonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}