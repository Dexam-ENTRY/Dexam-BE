package com.entry.dexam.domain.refresh.repositroy;

import com.entry.dexam.domain.refresh.entity.RefreshToken;

public interface RefreshTokenRedisRepository {
    RefreshToken consumeByToken(String code);

    void save(RefreshToken token);

    RefreshToken findByCode(String code);

    void delete(RefreshToken token);

    void deleteByToken(String token);
}
