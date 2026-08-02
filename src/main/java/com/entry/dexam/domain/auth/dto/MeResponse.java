package com.entry.dexam.domain.auth.dto;

import com.entry.dexam.domain.auth.enums.Role;

public record MeResponse(
        long id,
        String email,
        String name,
        Role role,
        ClassInfoDto classInfo
) {
}
