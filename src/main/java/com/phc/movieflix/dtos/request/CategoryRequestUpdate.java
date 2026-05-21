package com.phc.movieflix.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para atualização de uma categoria.")
public record CategoryRequestUpdate(
        @Schema(description = "Nome atualizado da categoria.", example = "Drama")
        @NotBlank(message = "Nome da categoria é obrigatório")
        String name
) {
}
