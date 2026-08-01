package com.entry.dexam.domain.auth.controller;

import com.entry.dexam.domain.auth.dto.MeResponse;
import com.entry.dexam.domain.auth.dto.ClassRequest;
import com.entry.dexam.domain.auth.service.AuthService;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

	@PatchMapping("/class")
    public ApiResponse<Void> updateClass(
        @RequestBody @Valid ClassRequest classRequest,
        @Parameter(hidden = true) @CurrentUserId Long id
    ) {
        authService.updateClass(classRequest, id);
		return ApiResponse.ok();
    }

    @GetMapping("/me")
    public ApiResponse<MeResponse> getMe(
        @Parameter(hidden = true) @CurrentUserId Long id
    ) {
        MeResponse meResponse = authService.getMe(id);
        return ApiResponse.ok(meResponse);
    }

    @DeleteMapping("/me")
    public ApiResponse<Void> deleteMe(
        @Parameter(hidden = true) @CurrentUserId Long id
    ) {
        authService.deleteMe(id);
        return ApiResponse.ok();
    }
}
