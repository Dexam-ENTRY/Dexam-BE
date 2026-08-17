package com.entry.dexam.domain.admin.controller;

import com.entry.dexam.domain.admin.dto.UpdateClassRequest;
import com.entry.dexam.domain.admin.dto.UpdateRoleRequest;
import com.entry.dexam.domain.admin.service.AdminService;
import com.entry.dexam.domain.auth.dto.ClassRequest;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PatchMapping("/users/{userId}/class")
    public ApiResponse<Void> updateClassRequest(
            @PathVariable Long userId,
            @RequestBody @Valid UpdateClassRequest updateClassRequest
    ) {
        adminService.updateClass(userId, updateClassRequest);
        return ApiResponse.ok();
    }

    @PatchMapping("/users/{userId}/role")
    public ApiResponse<Void> updateRoleRequest(
            @PathVariable Long userId,
            @RequestBody @Valid UpdateRoleRequest updateRoleRequest
        ) {
        adminService.updateRole(userId, updateRoleRequest);
        return ApiResponse.ok();
    }

}
