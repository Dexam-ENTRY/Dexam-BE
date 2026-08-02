package com.entry.dexam.domain.refresh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import static com.entry.dexam.domain.refresh.Refresh.REDIS_HEAD;
import static com.entry.dexam.domain.refresh.Refresh.REFRESH_TOKEN_ALIVE;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = REDIS_HEAD, timeToLive = REFRESH_TOKEN_ALIVE) // 2주
public class RefreshToken {
    @Id
    private String token;

    private Long userId;
}
