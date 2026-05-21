package com.phc.movieflix.config;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Resposta padrão para erros da API.")
public record ErrorResponse(
        @Schema(description = "Mensagem do erro.", example = "Recurso não encontrado")
        String message,

        @Schema(description = "Data e hora em que o erro ocorreu.", example = "2026-05-21T13:00:00Z")
        Instant timestamp
) {
    public static ErrorResponse of(String message) {
        return new ErrorResponse(message, Instant.now());
    }
}
