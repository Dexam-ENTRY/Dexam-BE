package com.entry.dexam.domain.refresh.repositroy;

import com.entry.dexam.domain.refresh.entity.ExchangeToken;

public interface ExchangeTokenRedisRepository {
    ExchangeToken consumeByCode(String code);

    void save(ExchangeToken token);

    ExchangeToken findByCode(String code);

    void delete(ExchangeToken token);
}