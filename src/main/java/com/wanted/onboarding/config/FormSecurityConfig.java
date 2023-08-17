package com.wanted.onboarding.config;

import com.wanted.onboarding.repo.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Configuration
 @EnableWebSecurity // Spring Security Filter 가 Spring Filter Chain에 등록됨
@RequiredArgsConstructor
public class FormSecurityConfig {

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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http
                /* CSRF */
                .csrf(AbstractHttpConfigurer::disable)

                /* URI VALIDATION */
                .authorizeHttpRequests(authCustomizer ->
                        authCustomizer
                                .requestMatchers("/form","/form/","/form/users").permitAll()
                                .requestMatchers("/form/user/**").permitAll()
                                .requestMatchers("/form/manager/**").hasRole("MANAGER")
                                .requestMatchers("/form/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
//
//                /* SIGN IN METHOD : FORM */
//                .formLogin(form -> form
//                                .loginPage("/form/loginForm")
//                                .loginProcessingUrl("/form/login")
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .defaultSuccessUrl("/form/signIn")
//                )
//                .logout(out->out.logoutUrl("/form/signOut"));

                return http.build();
    }

}
