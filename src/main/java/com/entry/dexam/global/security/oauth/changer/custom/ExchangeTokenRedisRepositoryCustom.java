package com.entry.dexam.global.security.oauth.changer.custom;

import com.entry.dexam.global.security.oauth.changer.ExchangeToken;

public interface ExchangeTokenRedisRepositoryCustom {
    ExchangeToken consumeByCode(String code);
}
