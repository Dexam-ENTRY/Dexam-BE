package com.entry.dexam.global.security.oauth;

import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.refresh.entity.ExchangeToken;
import com.entry.dexam.domain.refresh.repositroy.ExchangeTokenRedisRepository;
import com.entry.dexam.global.exception.ErrorCode;
import com.entry.dexam.global.exception.ErrorResponse;
import com.entry.dexam.global.security.jwt.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final ExchangeTokenRedisRepository exchangeTokenRedisRepository;
    private final ObjectMapper objectMapper;

    @Value("${frontend.oauth-callback-url}")
    private String callbackUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String email = (String) oAuth2User.getAttributes().get("email");

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

            Long id = user.getId();

            String code = RandomStringUtils.randomAlphanumeric(6);
            exchangeTokenRedisRepository.save(
                    ExchangeToken.builder().code(code).userId(id).build()
            );

            String targetUrl = callbackUrl + "?code=" + code;
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        } catch (UsernameNotFoundException e) {
            ErrorResponse<Void> errorResponse = ErrorResponse.from(ErrorCode.USER_NOT_FOUND);
            response.setStatus(ErrorCode.USER_NOT_FOUND.getStatusCode());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (Exception e) {
            ErrorResponse<Void> errorResponse = ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERR);
            response.setStatus(ErrorCode.INTERNAL_SERVER_ERR.getStatusCode());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }
}