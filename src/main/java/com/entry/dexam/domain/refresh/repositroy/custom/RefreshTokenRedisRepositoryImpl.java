package com.entry.dexam.domain.refresh.repositroy.custom;

import com.entry.dexam.domain.refresh.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.entry.dexam.domain.refresh.Refresh.REDIS_HEAD;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepositoryImpl
        implements RefreshTokenRedisRepositoryCustom {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String CONSUME_SCRIPT = """
    local value = redis.call('HGETALL', KEYS[1])

    if next(value) ~= nil then
        redis.call('DEL', KEYS[1])
    end

    return value
    """;


    @Override
    public RefreshToken consumeByToken(String token) {

        String key = REDIS_HEAD + ":" + token;

        List<String> result = redisTemplate.execute(
                new DefaultRedisScript<>(
                        CONSUME_SCRIPT,
                        List.class
                ),
                List.of(key)
        );

        if (result == null || result.isEmpty()) {
            return null;
        }

        return convertToRefreshToken(result);
    }


    private RefreshToken convertToRefreshToken(List<String> result) {

        String token = null;
        Long userId = null;

        for (int i = 0; i < result.size(); i += 2) {

            String field = result.get(i);
            String value = result.get(i + 1);

            if ("token".equals(field)) {
                token = value;
            }

            if ("userId".equals(field)) {
                userId = Long.valueOf(value);
            }
        }

        if (token == null || userId == null) {
            return null;
        }

        return RefreshToken.builder()
                .token(token)
                .userId(userId)
                .build();
    }
}
