package com.sntgzrr.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable()
                        .authorizeExchange()
                            .pathMatchers(HttpMethod.POST, "/registration/**").permitAll()
                            .anyExchange().authenticated()
                            .and()
                        .oauth2ResourceServer()
                            .jwt());
        return http.build();
    }
}
