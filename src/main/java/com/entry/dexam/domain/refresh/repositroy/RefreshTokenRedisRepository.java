package com.entry.dexam.domain.refresh.repositroy;

import com.entry.dexam.domain.refresh.entity.RefreshToken;
import com.entry.dexam.domain.refresh.repositroy.custom.RefreshTokenRedisRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String>, RefreshTokenRedisRepositoryCustom {
}
