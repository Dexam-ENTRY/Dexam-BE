package com.entry.dexam.global.security.oauth.changer.custom;

import com.entry.dexam.global.security.oauth.changer.ExchangeToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
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

        String accessToken = null;
        String code = null;

        for (int i = 0; i < result.size(); i += 2) {

            String field = result.get(i);
            String value = result.get(i + 1);

            if ("code".equals(field)) {
                code = value;
            }

            if ("accessToken".equals(field)) {
                accessToken = value;
            }
        }

        if (code == null) {
            return null;
        }

        if (accessToken == null) {
            return null;
        }

        return ExchangeToken.builder()
                .code(code)
                .accessToken(accessToken)
                .build();
    }
}
