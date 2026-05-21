package com.phc.movieflix.controller.docs;

import com.phc.movieflix.config.ErrorResponse;
import com.phc.movieflix.config.ValidationErrorResponse;
import com.phc.movieflix.dtos.request.MovieRequest;
import com.phc.movieflix.dtos.request.MovieRequestUpdate;
import com.phc.movieflix.dtos.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Filmes", description = "Gerenciamento do catálogo de filmes.")
@SecurityRequirement(name = "bearerAuth")
public interface MovieDocs {

    @Operation(summary = "Cadastrar filme", description = "Cria um novo filme e vincula categorias e streamings existentes.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Filme cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = MovieResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria ou streaming não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<MovieResponse> save(MovieRequest movieRequest);

    @Operation(summary = "Listar filmes", description = "Retorna todos os filmes cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filmes retornados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content)
    })
    ResponseEntity<List<MovieResponse>> findAll();

    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme específico pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme encontrado",
                    content = @Content(schema = @Schema(implementation = MovieResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<MovieResponse> findById(
            @Parameter(description = "ID do filme.", example = "1") Long id);

    @Operation(summary = "Atualizar filme", description = "Atualiza os dados de um filme pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = MovieResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Filme, categoria ou streaming não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<MovieResponse> update(
            @Parameter(description = "ID do filme.", example = "1") Long id,
            MovieRequestUpdate movieRequest);

    @Operation(summary = "Buscar filmes por categoria", description = "Retorna os filmes vinculados à categoria informada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filmes retornados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<MovieResponse>> findByCategory(
            @Parameter(description = "ID da categoria.", example = "1") Long category);

    @Operation(summary = "Listar melhores avaliações", description = "Retorna os 5 filmes com as maiores notas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filmes retornados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content)
    })
    ResponseEntity<List<MovieResponse>> findByRating();

    @Operation(summary = "Excluir filme", description = "Remove um filme pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Filme removido com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do filme.", example = "1") Long id);
}
