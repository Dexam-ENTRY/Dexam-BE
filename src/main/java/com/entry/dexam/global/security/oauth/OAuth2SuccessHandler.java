package com.entry.dexam.global.security.oauth;

import com.entry.dexam.global.security.jwt.JwtProvider;
import com.entry.dexam.global.security.oauth.changer.ExchangeToken;
import com.entry.dexam.global.security.oauth.changer.ExchangeTokenRedisRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final ExchangeTokenRedisRepository exchangeTokenRedisRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String email = (String) oAuth2User.getAttributes().get("email");

            String accessToken = jwtProvider.createAccessToken(email, "ROLE_USER");

            String code = RandomStringUtils.randomAlphanumeric(6);
            exchangeTokenRedisRepository.save(
                    ExchangeToken.builder().code(code).accessToken(accessToken).build()
            );

            String targetUrl = "http://localhost:8080/api/auth/token?code=" + code;
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        } catch (Exception e) {
            log.error("OAuth2 success handler error: ", e);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"success\":false,\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}