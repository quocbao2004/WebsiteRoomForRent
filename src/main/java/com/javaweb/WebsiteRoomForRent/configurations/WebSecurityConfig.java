package com.javaweb.WebsiteRoomForRent.configurations;

import com.javaweb.WebsiteRoomForRent.entities.Role;
import com.javaweb.WebsiteRoomForRent.filters.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.http.HttpMethod.*;

@Configuration
//@EnableMethodSecurity
@EnableWebSecurity
//@EnableWebMvc
@RequiredArgsConstructor

public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/email/send-email", apiPrefix),
                                    String.format("%s/users/login", apiPrefix),
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/check-username", apiPrefix),
                                    String.format("%s/password-reset/send-otp", apiPrefix),
                                    String.format("%s/password-reset/validate-otp", apiPrefix),
                                    String.format("%s/password-reset/reset-password", apiPrefix),
                                    String.format("%s/users/get-user",apiPrefix)
                            )
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/building/**", apiPrefix)).permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/customer/**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/image/**", apiPrefix)).permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/building/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/building/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/building/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(GET,
                                    String.format("%s/customer**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/customer/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/customer/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(POST,
                                    String.format("%s/image/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/image/**", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(POST,
                                    String.format("%s/users/edit-user", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(POST,
                                    String.format("%s/users/logout", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .requestMatchers(POST,
                                    String.format("%s/users/change-password", apiPrefix)).hasAnyRole(Role.ADMIN)
                            .anyRequest().authenticated();
                });
        return http.build();
    }
}