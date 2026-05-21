package com.phc.movieflix.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciais para autenticação.")
public record LoginRequest(
        @Schema(description = "E-mail do usuário.", example = "usuario@movieflix.com")
        @NotBlank(message = "Email deve ser informado")
        String email,

        @Schema(description = "Senha do usuário.", example = "senha1234", format = "password")
        @NotBlank(message = "Senha deve ser informada")
        String password
) {
}
