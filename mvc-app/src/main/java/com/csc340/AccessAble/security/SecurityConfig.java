package com.csc340.AccessAble.security;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private AppUserDetailsService customUserDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
    requestCache.setMatchingRequestParameterName(null);
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((authorize) -> authorize
            .dispatcherTypeMatchers(DispatcherType.FORWARD,
                DispatcherType.ERROR)
            .permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .requestMatchers("/static/**", "/css/**", "/customerpfp/**", "/*.jpg", "/*.png", "/*.gif").permitAll()
            .requestMatchers("/customers/my-reviews/**", "/customer/account/**", "/customer/listing/writereview/**", "/customer/favoritelistings/**").hasAuthority("CUSTOMER")
            .requestMatchers("/", "/customer/listing/**", "/customer/listings/**", "/home", "/customer/sign-up/**").permitAll()
            
            .anyRequest().authenticated())
        .formLogin(form -> form
          .loginPage("/customer/login")
          .loginProcessingUrl("/login")
          .failureUrl("/customer/login?error=true")
          .defaultSuccessUrl("/customer/account")     
          .permitAll()   
        )
        .exceptionHandling((x) -> x.accessDeniedPage("/403"))
        .logout(Customizer.withDefaults())
        .requestCache((cache) -> cache
            .requestCache(requestCache));

    return http.build();
  }

  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService).passwordEncoder(
        passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}