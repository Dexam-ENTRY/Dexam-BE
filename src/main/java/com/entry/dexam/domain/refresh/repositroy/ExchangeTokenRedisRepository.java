package com.entry.dexam.domain.refresh.repositroy;

import com.entry.dexam.domain.refresh.entity.ExchangeToken;
import com.entry.dexam.domain.refresh.repositroy.custom.ExchangeTokenRedisRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeTokenRedisRepository extends CrudRepository<ExchangeToken, String>, ExchangeTokenRedisRepositoryCustom {
    ExchangeToken findByCode(String code);
}