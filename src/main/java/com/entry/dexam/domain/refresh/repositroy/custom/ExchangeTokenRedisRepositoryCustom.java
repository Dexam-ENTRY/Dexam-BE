package com.entry.dexam.domain.refresh.repositroy.custom;

import com.entry.dexam.domain.refresh.entity.ExchangeToken;

public interface ExchangeTokenRedisRepositoryCustom {
    ExchangeToken consumeByCode(String code);
}
