package com.entry.dexam.domain.refresh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import static com.entry.dexam.domain.refresh.Refresh.EXCHANGE_TOKEN_ALIVE;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "exchange", timeToLive = EXCHANGE_TOKEN_ALIVE)
public class ExchangeToken {
    @Id
    private String code;

    private Long userId;
}