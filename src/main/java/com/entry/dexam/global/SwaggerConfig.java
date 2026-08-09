package com.entry.dexam.global;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String BEARER_AUTH = "bearerAuth";
    private static final String CSRF_TOKEN = "csrfToken";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Dexam API")
                        .description("""
                                Dexam API Documentation

                                Authentication:
                                - Access Token: Authorization Bearer Token
                                - Refresh Token: HttpOnly Cookie
                                - CSRF Token: X-XSRF-TOKEN Header
                                """)
                        .version("v1.0.0")
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(BEARER_AUTH)
                )
                .components(
                        new Components()
                                // Access Token
                                .addSecuritySchemes(
                                        BEARER_AUTH,
                                        new SecurityScheme()
                                                .name(BEARER_AUTH)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )

                                // CSRF Token
                                .addSecuritySchemes(
                                        CSRF_TOKEN,
                                        new SecurityScheme()
                                                .name("X-XSRF-TOKEN")
                                                .type(SecurityScheme.Type.APIKEY)
                                                .in(SecurityScheme.In.HEADER)
                                )
                );
    }
}