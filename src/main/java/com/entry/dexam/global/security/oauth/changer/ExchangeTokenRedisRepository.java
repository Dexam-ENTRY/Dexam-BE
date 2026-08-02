package com.entry.dexam.global.security.oauth.changer;

import com.entry.dexam.global.security.oauth.changer.custom.ExchangeTokenRedisRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeTokenRedisRepository extends CrudRepository<ExchangeToken, String>, ExchangeTokenRedisRepositoryCustom {
    ExchangeToken findByCode(String code);
}