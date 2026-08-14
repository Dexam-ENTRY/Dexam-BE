package com.entry.dexam.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.entry.dexam.global.security.jwt.JwtAuthenticationFilter;
import com.entry.dexam.global.security.oauth.CustomOAuth2UserService;
import com.entry.dexam.global.security.oauth.OAuth2SuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        RequestMatcher csrfMatcher = new OrRequestMatcher(
            PathPatternRequestMatcher.withDefaults()
                .matcher("/api/refresh")
        );

        CookieCsrfTokenRepository csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        csrfTokenRepository.setCookieCustomizer(cookie -> cookie
                .secure(true)
                .sameSite("Lax")
        );

        http
        .httpBasic(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .csrf(csrf -> csrf
            .requireCsrfProtectionMatcher(csrfMatcher)
            .csrfTokenRepository(csrfTokenRepository)
            .csrfTokenRequestHandler(
                new CsrfTokenRequestAttributeHandler()
            )
        )
        .sessionManagement(session -> session
                .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))

        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/class", "/api/auth/me").authenticated()
                .requestMatchers("/api/auth/**", "/login/**", "/oauth2/**", "/api/refresh/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/api/admin/evaluation/**").hasRole("CLASS_ADMIN")
                .requestMatchers("/api/admin/schedules/**").hasAnyRole("CLASS_ADMIN", "ADMIN")
                .anyRequest().authenticated()
        )

        .oauth2Login(oauth2 -> oauth2
                .authorizationEndpoint(authorization -> authorization
                        .baseUri("/api/auth/oauth")
                )
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService)
                )
                .successHandler(oAuth2SuccessHandler)
        )

        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

        .exceptionHandling(exceptionHandling -> exceptionHandling      // 추가
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}