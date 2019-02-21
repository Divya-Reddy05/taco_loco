package com.taco.loco.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration {

    @Configuration
    @EnableWebSecurity
    public static class Default extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // CSRF protection doesn't make sense for REST API's, so disable it by default.
            http.csrf().disable();
        }
    }

    /**
     * This configuration overrides Spring Boot's default configuration where it
     * adds a default user with a generated password that's printed to STDOUT.
     * <p>
     * When an application defines a WebSecurityConfigurerAdapter we back off and let the
     * application configure Spring Security.
     */
    @Configuration
    @ConditionalOnMissingBean(WebSecurityConfigurerAdapter.class)
    public static class DisableDefaultUser {
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication();
        }
    }

}
