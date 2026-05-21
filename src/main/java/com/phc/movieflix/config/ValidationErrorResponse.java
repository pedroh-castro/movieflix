package com.phc.movieflix.config;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Map;

@Schema(description = "Resposta padrão para erros de validação.")
public record ValidationErrorResponse(
        @Schema(description = "Mapa com o nome do campo e a mensagem de validação.", example = "{\"name\":\"Nome é obrigatório\"}")
        Map<String, String> errors,

        @Schema(description = "Data e hora em que o erro ocorreu.", example = "2026-05-21T13:00:00Z")
        Instant timestamp
) {
    public static ValidationErrorResponse of(Map<String, String> errors) {
        return new ValidationErrorResponse(errors, Instant.now());
    }
}
