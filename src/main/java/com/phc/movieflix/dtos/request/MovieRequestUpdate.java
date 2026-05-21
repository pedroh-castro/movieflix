package com.phc.movieflix.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Dados para atualização parcial de filme.")
public record MovieRequestUpdate(
        @Schema(description = "Título atualizado do filme.", example = "Interestelar")
        String title,

        @Schema(description = "Descrição atualizada do filme.", example = "Uma jornada espacial para salvar a humanidade.")
        String description,

        @Schema(description = "Data de lançamento no formato dd/MM/yyyy.", example = "06/11/2014", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        @Schema(description = "Nota atualizada do filme.", example = "9.0", minimum = "0")
        Double rating,

        @Schema(description = "IDs atualizados das categorias vinculadas ao filme.", example = "[1, 2]")
        Set<Long> categories,

        @Schema(description = "IDs atualizados dos streamings vinculados ao filme.", example = "[1, 3]")
        Set<Long> streamings
) {
}
