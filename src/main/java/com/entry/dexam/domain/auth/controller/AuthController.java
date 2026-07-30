package com.entry.dexam.domain.auth.controller;

import com.entry.dexam.domain.auth.dto.MeResponse;
import com.entry.dexam.domain.auth.dto.SetClassRequest;
import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.service.AuthService;
import com.entry.dexam.global.anotations.CurrentUser.CurrentUser;
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
        @Parameter(hidden = true) @CurrentUser User user
    ) {
        authService.updateClass(setClassRequest, user);
		return ApiResponse.ok();
    }

    @GetMapping("/me")
    public ApiResponse<MeResponse> getMe(
        Authentication authentication,
        @Parameter(hidden = true) @CurrentUser User user
    ) {
        MeResponse meResponse = authService.getMe(user);
        return ApiResponse.ok(meResponse);
    }

    @DeleteMapping("/me")
    public ApiResponse<Void> deleteMe(
        Authentication authentication,
        @Parameter(hidden = true) @CurrentUser User user
    ) {
        authService.deleteMe(user);
        return ApiResponse.ok();
    }
}
