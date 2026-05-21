package com.phc.movieflix.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para cadastro de usuário.")
public record UserRequest(

        @Schema(description = "Nome completo do usuário.", example = "Pedro Carvalho")
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @Schema(description = "E-mail usado para login.", example = "pedro@movieflix.com")
        @Email(message = "E-mail é obrigatório")
        @NotBlank(message = "E-mail não pode estar nulo")
        String email,

        @Schema(description = "Senha com no mínimo 8 caracteres.", example = "senha1234", format = "password")
        @NotBlank(message = "Password é obrigatório")
        @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
        String password
) {
}
