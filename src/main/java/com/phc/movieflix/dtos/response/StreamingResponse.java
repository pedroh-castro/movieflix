package com.phc.movieflix.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Serviço de streaming cadastrado.")
public record StreamingResponse(
        @Schema(description = "Identificador do streaming.", example = "1")
        Long id,

        @Schema(description = "Nome do serviço de streaming.", example = "Netflix")
        String name
) {
}
