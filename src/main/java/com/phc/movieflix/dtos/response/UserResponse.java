package com.phc.movieflix.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Usuário cadastrado.")
public record UserResponse(
        @Schema(description = "Identificador do usuário.", example = "1")
        Long id,

        @Schema(description = "Nome do usuário.", example = "Pedro Carvalho")
        String name,

        @Schema(description = "E-mail do usuário.", example = "pedro@movieflix.com")
        String email
) {
}
