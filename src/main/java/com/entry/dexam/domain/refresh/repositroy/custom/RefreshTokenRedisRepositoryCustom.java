package com.entry.dexam.domain.refresh.repositroy.custom;

import com.entry.dexam.domain.refresh.entity.RefreshToken;

public interface RefreshTokenRedisRepositoryCustom {
    RefreshToken consumeByToken(String token);
}
