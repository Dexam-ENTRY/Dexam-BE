package com.entry.dexam.domain.auth.controller;

import com.entry.dexam.domain.auth.dto.MeResponse;
import com.entry.dexam.domain.auth.dto.SetClassRequest;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.service.AuthService;
import com.entry.dexam.global.anotations.CurrentUser.CurrentUserEmail;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        @RequestBody @Valid SetClassRequest setClassRequest,
        @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        authService.updateClass(setClassRequest, email);
		return ApiResponse.ok();
    }

    @GetMapping("/me")
    public ApiResponse<MeResponse> getMe(
        @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        MeResponse meResponse = authService.getMe(email);
        return ApiResponse.ok(meResponse);
    }

    @DeleteMapping("/me")
    public ApiResponse<Void> deleteMe(
        @Parameter(hidden = true) @CurrentUserEmail String email
    ) {
        authService.deleteMe(email);
        return ApiResponse.ok();
    }
}
