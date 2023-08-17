//package com.wanted.onboarding.config;
//
//import com.wanted.onboarding.jwt.JwtAuthenticationFilter;
//import com.wanted.onboarding.jwt.JwtAuthorizationFilter;
//import com.wanted.onboarding.repo.UserRepository;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import java.io.IOException;
//
////@Configuration
//// @EnableWebSecurity // Spring Security Filter 가 Spring Filter Chain에 등록됨
////@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final AuthenticationConfiguration authenticationConfiguration;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CorsConfig corsConfig;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }s
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//        http
//                 Setting
//                // csrf off
//                .csrf(AbstractHttpConfigurer::disable)
//                // cors 허용
//                .addFilter(corsConfig.corsFilter())
//                // turn off session :: login only by jwt
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                // turn off form method login :: login only by jwt
//                .formLogin(form -> form.disable())
//                // turn off request header method login :: login only by jwt
//                .httpBasic(hb -> hb.disable())
//
//                 AUTH
//                // authentication : verifies the identity of a user
//                // .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                // authorization : determines their access rights
//                // .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
//
//                 URI VALIDATION
//                // request check by uri
////                .authorizeHttpRequests(
////                        authCustomizer -> authCustomizer
////                                .requestMatchers("/v1/**").permitAll()
////                                .anyRequest().authenticated()
////                )
//
//                 ERR HANDLE
//                .exceptionHandling(excep -> excep
//                        .accessDeniedHandler(new AccessDeniedHandler() {
//                            @Override
//                            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                                // 권한 문제가 발생했을 때 이 부분을 호출한다.
//                                response.setStatus(403);
//                                response.setCharacterEncoding("utf-8");
//                                response.setContentType("text/html; charset=UTF-8");
//                                response.getWriter().write("권한이 없는 사용자입니다.");
//                            }
//                        })
//                        .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                            @Override
//                            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                                // 인증문제가 발생했을 때 이 부분을 호출한다.
//                                response.setStatus(401);
//                                response.setCharacterEncoding("utf-8");
//                                response.setContentType("text/html; charset=UTF-8");
//                                response.getWriter().write("인증되지 않은 사용자입니다.");
//                            }
//                        })
//                );
//
//        return http.build();
//    }
//
//}
