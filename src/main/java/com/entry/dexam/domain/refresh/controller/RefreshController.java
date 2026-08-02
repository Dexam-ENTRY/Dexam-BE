package com.entry.dexam.domain.refresh.controller;

import com.entry.dexam.domain.evaluation.dto.EvaluationAddRequest;
import com.entry.dexam.domain.refresh.component.CookieProvider;
import com.entry.dexam.domain.refresh.dto.request.ExchangeTokenRequest;
import com.entry.dexam.domain.refresh.dto.response.AccessTokenResponse;
import com.entry.dexam.domain.refresh.dto.response.TokenDto;
import com.entry.dexam.domain.refresh.service.RefreshService;
import com.entry.dexam.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.entry.dexam.domain.refresh.Refresh.REFRESH_TOKEN_COOKIE_NAME;

@RestController
@RequestMapping("/api/refresh")
@RequiredArgsConstructor
public class RefreshController {

    private final RefreshService refreshService;
    private final CookieProvider cookieProvider;

    @PostMapping("/exchange")
    public ResponseEntity<ApiResponse<AccessTokenResponse>> exchangeToken(
        @RequestBody @Valid ExchangeTokenRequest exchangeTokenRequest
    ) {
        TokenDto tokenDto = refreshService.exchangeToken(exchangeTokenRequest.code());
        ResponseCookie refreshCookie = cookieProvider.createRefreshTokenCookie(tokenDto.refreshToken());

        return ResponseEntity.ok()
            .header(
                HttpHeaders.SET_COOKIE,
                refreshCookie.toString()
            )
            .body(
                ApiResponse.ok(
                    new AccessTokenResponse(tokenDto.accessToken())
                )
            );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccessTokenResponse>> reissueToken(
        @CookieValue(
            value = REFRESH_TOKEN_COOKIE_NAME,
            required = false
        ) String refreshToken
    ) {
        TokenDto tokenDto = refreshService.reissueRefreshToken(refreshToken);
        ResponseCookie refreshCookie = cookieProvider.createRefreshTokenCookie(tokenDto.refreshToken());

        return ResponseEntity.ok()
            .header(
                HttpHeaders.SET_COOKIE,
                refreshCookie.toString()
            )
            .body(
                ApiResponse.ok(
                    new AccessTokenResponse(tokenDto.accessToken())
                )
            );
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteToken(
        @CookieValue(
            value = REFRESH_TOKEN_COOKIE_NAME,
            required = false
        ) String refreshToken
    ) {
        refreshService.deleteToken(refreshToken);
        ResponseCookie cookie = cookieProvider.deleteRefreshTokenCookie();

        return ResponseEntity.ok()
            .header(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
            )
            .body(
                ApiResponse.ok()
            );
    }

}
