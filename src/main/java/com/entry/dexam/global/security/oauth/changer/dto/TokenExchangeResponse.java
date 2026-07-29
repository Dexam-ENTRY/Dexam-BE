package com.entry.dexam.global.security.oauth.changer.dto;

import lombok.Builder;

@Builder
public record TokenExchangeResponse(
        String accessToken
) {

}
