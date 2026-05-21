package com.phc.movieflix.controller.docs;

import com.phc.movieflix.config.ErrorResponse;
import com.phc.movieflix.config.ValidationErrorResponse;
import com.phc.movieflix.dtos.request.CategoryRequest;
import com.phc.movieflix.dtos.request.CategoryRequestUpdate;
import com.phc.movieflix.dtos.response.CategoryResponse;
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

@Tag(name = "Categorias", description = "Gerenciamento das categorias dos filmes.")
@SecurityRequirement(name = "bearerAuth")
public interface CategoryDocs {

    @Operation(summary = "Cadastrar categoria", description = "Cria uma nova categoria de filme.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content)
    })
    ResponseEntity<CategoryResponse> addCategory(CategoryRequest dto);

    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categorias retornadas com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content)
    })
    ResponseEntity<List<CategoryResponse>> getAllCategories();

    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "ID da categoria.", example = "1") Long id);

    @Operation(summary = "Excluir categoria", description = "Remove uma categoria pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria removida com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Void> deleteCategoryById(
            @Parameter(description = "ID da categoria.", example = "1") Long id);

    @Operation(summary = "Atualizar categoria", description = "Atualiza os dados de uma categoria pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<CategoryResponse> updateCategoryById(
            @Parameter(description = "ID da categoria.", example = "1") Long id,
            CategoryRequestUpdate request);
}
