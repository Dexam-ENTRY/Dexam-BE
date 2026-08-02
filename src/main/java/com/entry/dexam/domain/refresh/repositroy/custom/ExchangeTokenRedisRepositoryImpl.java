package com.entry.dexam.domain.refresh.repositroy.custom;

import com.entry.dexam.domain.refresh.entity.ExchangeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExchangeTokenRedisRepositoryImpl
        implements ExchangeTokenRedisRepositoryCustom {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String CONSUME_SCRIPT = """
    local value = redis.call('HGETALL', KEYS[1])

    if next(value) ~= nil then
        redis.call('DEL', KEYS[1])
    end

    return value
    """;


    @Override
    public ExchangeToken consumeByCode(String code) {

        String key = "exchange:" + code;

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
