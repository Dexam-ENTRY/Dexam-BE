package com.entry.dexam.domain.refresh.repositroy.custom;

import com.entry.dexam.domain.refresh.entity.ExchangeToken;
import com.entry.dexam.domain.refresh.repositroy.ExchangeTokenRedisRepository;
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
public class ExchangeTokenRedisRepositoryImpl
        implements ExchangeTokenRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String CONSUME_SCRIPT = """
    local value = redis.call('HGETALL', KEYS[1])

    if next(value) ~= nil then
        redis.call('DEL', KEYS[1])
    end

    return value
    """;

    private String createHead(String value) {
        return EXCHANGE_REDIS_HEAD + ":" + value;
    }

    @Override
    public ExchangeToken consumeByCode(String code) {

        String key = createHead(code);

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

        return convertToExchangeToken(result);
    }

    @Override
    public void save(ExchangeToken token) {
        String key = createHead(token.getCode());

        Map<String, String> data = Map.of(
                "code", token.getCode(),
                "userId", String.valueOf(token.getUserId())
        );

        redisTemplate.opsForHash()
                .putAll(key, data);

        redisTemplate.expire(
                key,
                Duration.ofSeconds(EXCHANGE_TOKEN_ALIVE)
        );
    }

    @Override
    public ExchangeToken findByCode(String code) {
        String key = createHead(code);

        Map<Object, Object> result =
                redisTemplate.opsForHash()
                        .entries(key);

        if (result.isEmpty()) {
            return null;
        }

        return ExchangeToken.builder()
                .code((String) result.get("code"))
                .userId(Long.valueOf(
                        (String) result.get("userId")
                ))
                .build();
    }

    @Override
    public void delete(ExchangeToken token) {
        String key = createHead(token.getCode());

        redisTemplate.delete(key);
    }


    private ExchangeToken convertToExchangeToken(List<String> result) {

        Long userId = null;
        String code = null;

        for (int i = 0; i < result.size(); i += 2) {

            String field = result.get(i);
            String value = result.get(i + 1);

            if ("code".equals(field)) {
                code = value;
            }

            if ("userId".equals(field)) {
                userId = Long.valueOf(value);
            }
        }

        if (code == null) {
            return null;
        }

        if (userId == null) {
            return null;
        }

        return ExchangeToken.builder()
                .code(code)
                .userId(userId)
                .build();
    }
}
