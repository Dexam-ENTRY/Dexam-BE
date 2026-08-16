package com.entry.dexam.domain.admin.dto;

import com.entry.dexam.domain.auth.enums.Role;
import jakarta.validation.constraints.NotNull;

public record UpdateRoleRequest(
        @NotNull(message = "역할은 필수입니다.")
        Role role
) {
}
