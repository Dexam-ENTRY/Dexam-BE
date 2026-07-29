package com.entry.dexam.global.security.oauth.changer;

import org.springframework.data.repository.CrudRepository;

public interface ExchangeTokenRedisRepository extends CrudRepository<ExchangeToken, String> {
    ExchangeToken findByCode(String code);
}