package com.entry.dexam.domain.refresh.service;

import com.entry.dexam.domain.auth.entity.User;
import com.entry.dexam.domain.auth.repository.UserRepository;
import com.entry.dexam.domain.refresh.component.RefreshTokenGenerator;
import com.entry.dexam.domain.refresh.dto.response.TokenDto;
import com.entry.dexam.domain.refresh.entity.ExchangeToken;
import com.entry.dexam.domain.refresh.entity.RefreshToken;
import com.entry.dexam.domain.refresh.repositroy.ExchangeTokenRedisRepository;
import com.entry.dexam.domain.refresh.repositroy.RefreshTokenRedisRepository;
import com.entry.dexam.global.exception.exceptions.TokenNotFoundException;
import com.entry.dexam.global.exception.exceptions.UserNotFoundException;
import com.entry.dexam.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final UserRepository userRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final JwtProvider jwtProvider;
    private final ExchangeTokenRedisRepository exchangeTokenRedisRepository;

    public String createRefreshToken(Long userId) {
        String token = refreshTokenGenerator.generate();

        RefreshToken refreshToken = RefreshToken.builder().token(token).userId(userId).build();
        refreshTokenRedisRepository.save(refreshToken);

        return token;
    }

    public String issueAccessToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return jwtProvider.createAccessToken(user.getId(), user.getRole().getKey());
    }

    private RefreshToken getRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRedisRepository.consumeByToken(token);

        if (refreshToken == null) {
            throw TokenNotFoundException.EXCEPTION;
        }

        return refreshToken;
    }

    public TokenDto reissueRefreshToken(String token) {
        if (token == null) {
            throw TokenNotFoundException.EXCEPTION;
        }

        RefreshToken refreshToken = getRefreshToken(token);
        String newRefreshToken = createRefreshToken(refreshToken.getUserId());
        String newAccessToken = issueAccessToken(refreshToken.getUserId());

        return new TokenDto(newRefreshToken, newAccessToken);
    }

    public void deleteToken(String token) {
        getRefreshToken(token);
    }

    public TokenDto exchangeToken(String code) {
        ExchangeToken exchangeToken = exchangeTokenRedisRepository.consumeByCode(code);
        if (exchangeToken == null) {
            throw TokenNotFoundException.EXCEPTION;
        }

        Long userId = exchangeToken.getUserId();

        String refreshToken = createRefreshToken(userId);
        String accessToken = issueAccessToken(userId);

        return new TokenDto(refreshToken, accessToken);
    }
}
