package com.phc.movieflix.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para atualização de um serviço de streaming.")
public record StreamingRequestUpdate(
        @Schema(description = "Nome atualizado do serviço de streaming.", example = "Prime Video")
        @NotBlank(message = "Nome do Streaming é obrigatório")
        String name
) {
}
