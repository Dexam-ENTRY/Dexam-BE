package com.entry.dexam.domain.auth.controller;

import com.entry.dexam.domain.auth.dto.MeResponse;
import com.entry.dexam.domain.auth.dto.SetClassRequest;
import com.entry.dexam.domain.auth.service.AuthService;
import com.entry.dexam.global.dto.ApiResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

	@PatchMapping("/class")
    public ApiResponse<Void> updateClass(
        @RequestBody SetClassRequest setClassRequest
    ) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        authService.updateClass(setClassRequest, email);
		return ApiResponse.ok();
    }

    @GetMapping("/me")
    public ApiResponse<MeResponse> me() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        MeResponse meResponse = authService.getMe(email);
        return ApiResponse.ok(meResponse);
    }
}
