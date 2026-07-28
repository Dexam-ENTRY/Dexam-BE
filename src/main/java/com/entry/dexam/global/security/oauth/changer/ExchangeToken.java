package com.entry.dexam.global.security.oauth.changer;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "exchange", timeToLive = 86400)
public class ExchangeToken {
    @Id
    private String code;

    private String accessToken;
}