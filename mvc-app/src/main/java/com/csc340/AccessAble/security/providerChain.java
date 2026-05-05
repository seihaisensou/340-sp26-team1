package com.csc340.AccessAble.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class providerChain {

        @Bean
        @Order(2)
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .securityMatcher("/provider/**")
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/",
                                                                "/home",
                                                                "/index",
                                                                "/provider/login",
                                                                "/provider/sign-up",
                                                                "/provider/signup",
                                                                "/css/**",
                                                                "/images/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/provider/login")
                                                .loginProcessingUrl("/provider/login/perform_login")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/provider/account", true)
                                                .failureUrl("/provider/login?error=true")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/provider/logout")
                                                .logoutSuccessUrl("/provider/login")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID"));

                return http.build();
        }
}