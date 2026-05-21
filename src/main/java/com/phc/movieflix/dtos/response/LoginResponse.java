package com.phc.movieflix.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Token de autenticação JWT.")
public record LoginResponse(
        @Schema(description = "Token JWT usado no header Authorization.", example = "eyJhbGciOiJIUzI1NiJ9...")
        String token
) {
}
