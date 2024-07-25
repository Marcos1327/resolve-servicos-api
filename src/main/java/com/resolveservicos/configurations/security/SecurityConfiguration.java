package com.resolveservicos.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/v1/users/create", // Endpoint to create a new user
            "/api/v1/users/login"   // Endpoint to login
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED_ADMIN = {
            "/api/v1/schedulings/**",
            "/api/v1/customers/**",
            "/api/v1/users/**",
            "/api/v1/service-types/**",
    };

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED_CUSTOMER = {
            "/api/v1/schedulings"
    };

    public SecurityConfiguration(UserAuthenticationFilter userAuthenticationFilter) {
        this.userAuthenticationFilter = userAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(request -> request
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.GET, ENDPOINTS_WITH_AUTHENTICATION_REQUIRED_CUSTOMER).hasRole("CUSTOMER")
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED_ADMIN).hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.POST).hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMINISTRATOR")
                        .requestMatchers(HttpMethod.PATCH).hasRole("ADMINISTRATOR")
                        .anyRequest().authenticated())
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
