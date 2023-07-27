package com.example.userjoinlogin.config;

import com.example.userjoinlogin.exception.CustomAuthenticationEntryPoint;
import com.example.userjoinlogin.filter.JwtTokenFilter;
import com.example.userjoinlogin.service.UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    @Value("${jwt.secret-key}")
    private String key;

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/auth/test").authenticated()
                        .requestMatchers("/users/join", "/users/login").permitAll()
                        .and()
                        .addFilterBefore(new JwtTokenFilter(key, userService), UsernamePasswordAuthenticationFilter.class));
        return http.build();
    }

//    @Bean
//    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");
//
//             http.securityContext((context) -> context
//                             .requireExplicitSave(false))
//                     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                     .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/login")
//                             .csrfTokenRepository(CookieCsrfTokenRepository.with))
//                     .requestMatchers("/auth/test").authenticated()
//                     .requestMatchers("/users/join", "/users/login").permitAll()
//                     .and().formLogin()
//                     .and().httpBasic();
//             return http.build();
//    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
