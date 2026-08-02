package com.entry.dexam.domain.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    CLASS_ADMIN("ROLE_CLASS_ADMIN"),
    ADMIN("ROLE_ADMIN");

    private final String key;
}