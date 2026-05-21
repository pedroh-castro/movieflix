package com.phc.movieflix.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para cadastro de um serviço de streaming.")
public record StreamingRequest(
        @Schema(description = "Nome do serviço de streaming.", example = "Netflix")
        @NotBlank(message = "Nome do Streaming é obrigatório")
        String name
) {
}
