package com.entry.dexam.domain.refresh.component;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.entry.dexam.domain.refresh.Refresh.REFRESH_TOKEN_ALIVE_DAYS;
import static com.entry.dexam.domain.refresh.Refresh.REFRESH_TOKEN_COOKIE_NAME;

@Component
public class CookieProvider {

    public ResponseCookie createRefreshTokenCookie(
            String refreshToken
    ) {
        return ResponseCookie.from(
                        REFRESH_TOKEN_COOKIE_NAME,
                        refreshToken
                )
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(REFRESH_TOKEN_ALIVE_DAYS))
                .sameSite("Lax")
                .build();
    }

    public ResponseCookie deleteRefreshTokenCookie() {

        return ResponseCookie.from(
                        REFRESH_TOKEN_COOKIE_NAME,
                        ""
                )
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
    }
}