package com.phc.movieflix.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Dados para cadastro de filme.")
public record MovieRequest(
        @Schema(description = "Título do filme.", example = "Interestelar")
        @NotBlank(message = "Título do filme é obrigatório")
        String title,

        @Schema(description = "Descrição ou sinopse do filme.", example = "Uma equipe viaja por um buraco de minhoca em busca de um novo lar para a humanidade.")
        @NotBlank(message = "Informe uma descrição")
        String description,

        @Schema(description = "Data de lançamento no formato dd/MM/yyyy.", example = "06/11/2014", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @NotNull(message = "Informe uma data")
        LocalDate releaseDate,

        @Schema(description = "Nota do filme.", example = "8.6", minimum = "0")
        @PositiveOrZero(message = "Informe um rating válido")
        double rating,

        @Schema(description = "IDs das categorias vinculadas ao filme.", example = "[1, 2]")
        @NotEmpty(message = "Categoria deve ser preenchida")
        Set<Long> categories,

        @Schema(description = "IDs dos streamings vinculados ao filme.", example = "[1, 3]")
        @NotEmpty(message = "Streaming deve ser preenchido")
        Set<Long> streamings
    ) {
}
