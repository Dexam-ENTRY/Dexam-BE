package com.entry.dexam.global.security;

import java.io.IOException;

import com.entry.dexam.global.exception.ErrorCode;
import com.entry.dexam.global.exception.ErrorResponse;
import tools.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

	// 인가가 되지 않음
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exception
    ) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ErrorResponse<Void> errorResponse =
                ErrorResponse.from(ErrorCode.FORBIDDEN);

        response.getWriter().write(
                objectMapper.writeValueAsString(errorResponse)
        );
    }
}