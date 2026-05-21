package com.phc.movieflix.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Categoria cadastrada.")
public record CategoryResponse(
        @Schema(description = "Identificador da categoria.", example = "1")
        Long id,

        @Schema(description = "Nome da categoria.", example = "Ação")
        String name
) {
}
