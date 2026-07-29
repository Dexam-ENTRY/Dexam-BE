package com.entry.dexam.global.security;

import java.io.IOException;

import com.entry.dexam.global.exception.ErrorCode;
import com.entry.dexam.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

	// 인증이 되지 않음
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ErrorResponse<Void> errorResponse =
                ErrorResponse.errorCodeFrom(ErrorCode.UNAUTHORIZED);

        response.getWriter().write(
                objectMapper.writeValueAsString(errorResponse)
        );
    }
}