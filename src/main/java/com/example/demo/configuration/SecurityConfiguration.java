package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()  // Permetti accesso pubblico
                .anyRequest().authenticated()  // Proteggi tutto il resto
            )
            .httpBasic(httpBasic -> httpBasic.disable())  // Disabilita autenticazione HTTP Basic
            .csrf(csrf -> csrf.disable())  // Disabilita CSRF per API REST
            .build();
    }
}