package com.entry.dexam.domain.refresh.repositroy.custom;

import com.entry.dexam.domain.refresh.entity.RefreshToken;
import com.entry.dexam.domain.refresh.repositroy.RefreshTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.entry.dexam.domain.refresh.Refresh.*;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepositoryImpl
        implements RefreshTokenRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String CONSUME_SCRIPT = """
    local value = redis.call('HGETALL', KEYS[1])

    if next(value) ~= nil then
        redis.call('DEL', KEYS[1])
    end

    return value
    """;

    private String createHead(String value) {
        return REFRESH_REDIS_HEAD + ":" + value;
    }


    @Override
    public RefreshToken consumeByToken(String token) {

        String key = createHead(token);

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

    @Override
    public void save(RefreshToken token) {
        String key = createHead(token.getToken());

        Map<String, String> data = Map.of(
            "token", token.getToken(),
            "userId", String.valueOf(token.getUserId())
        );

        redisTemplate.opsForHash()
            .putAll(key, data);

        redisTemplate.expire(
            key,
            Duration.ofSeconds(REFRESH_TOKEN_ALIVE)
        );
    }

    @Override
    public RefreshToken findByCode(String code) {
        String key = createHead(code);

        Map<Object, Object> result =
                redisTemplate.opsForHash()
                        .entries(key);

        if (result.isEmpty()) {
            return null;
        }

        return RefreshToken.builder()
                .token((String) result.get("token"))
                .userId(Long.valueOf(
                    (String) result.get("userId")
                ))
                .build();
    }

    @Override
    public void delete(RefreshToken token) {
        String key = createHead(token.getToken());

        redisTemplate.delete(key);
    }

    @Override
    public void deleteByToken(String token) {
        String key = createHead(token);

        redisTemplate.delete(key);
    }
}
