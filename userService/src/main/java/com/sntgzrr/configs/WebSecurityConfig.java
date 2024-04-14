package com.sntgzrr.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers(
                    HttpMethod.POST,
                    "/users"
            );

            web.ignoring().requestMatchers(
                            HttpMethod.OPTIONS,
                            "/**"
                    )
                    .requestMatchers("/v3/api-docs/**", "/configuration/**", "/swagger-ui/**",
                            "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/api-docs/**");

        };
    }
}
