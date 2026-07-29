package com.entry.dexam.global.security.oauth.changer;

import com.entry.dexam.global.security.oauth.changer.dto.TokenExchangeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/token")
@RequiredArgsConstructor
public class TokenExchangerController {
    private final ExchangeTokenRedisRepository exchangeTokenRedisRepository;

	@GetMapping("")
    public TokenExchangeResponse getToken(
            @RequestParam(value="code") String code
    ) {
        ExchangeToken exchangeToken = exchangeTokenRedisRepository.findByCode(code);
        exchangeTokenRedisRepository.delete(exchangeToken);
		return TokenExchangeResponse.builder().accessToken(
                exchangeToken.getAccessToken()
        ).build();
    }
}
