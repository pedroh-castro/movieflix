package com.phc.movieflix.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Filme cadastrado.")
public record MovieResponse(
        @Schema(description = "Identificador do filme.", example = "1")
        Long id,

        @Schema(description = "Título do filme.", example = "Interestelar")
        String title,

        @Schema(description = "Descrição ou sinopse do filme.", example = "Uma equipe viaja por um buraco de minhoca em busca de um novo lar para a humanidade.")
        String description,

        @Schema(description = "Data de lançamento no formato dd/MM/yyyy.", example = "06/11/2014", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        @Schema(description = "Nota do filme.", example = "8.6")
        double rating,

        @Schema(description = "Categorias vinculadas ao filme.")
        List<CategoryResponse> categories,

        @Schema(description = "Serviços de streaming vinculados ao filme.")
        List<StreamingResponse> streamings
) {
}
