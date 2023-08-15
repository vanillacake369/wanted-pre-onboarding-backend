package com.wanted.onboarding.config;

import com.wanted.onboarding.jwt.JwtAuthenticationFilter;
import com.wanted.onboarding.jwt.JwtAuthorizationFilter;
import com.wanted.onboarding.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

 @Configuration
 @EnableWebSecurity // Spring Security Filter 가 Spring Filter Chain에 등록됨
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CorsConfig corsConfig;

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .csrf(AbstractHttpConfigurer::disable) // csrf off
                .addFilter(corsConfig.corsFilter()) // cors 허용
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // turn off session :: login only by jwt
                .formLogin(form->form.disable()) // turn off form method login :: login only by jwt
                .httpBasic(hb->hb.disable()); // turn off request header method login :: login only by jwt

                /*.addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeHttpRequests(
                        authCustomizer -> authCustomizer
                                .requestMatchers("/api/v1/user/**")
                                .permitAll()
                                .requestMatchers("/api/**").authenticated()
                );*/

        return http.build();
    }

}
