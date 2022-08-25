package com.sparta.assignmentjwt.config;

import com.sparta.assignmentjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "favicon.ico",
                        "/error"
                );
    }


    @Bean
    public DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable();

        http
                .authorizeRequests()
                .antMatchers("/api/members/signup").permitAll()
                .antMatchers("/api/members/login").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}