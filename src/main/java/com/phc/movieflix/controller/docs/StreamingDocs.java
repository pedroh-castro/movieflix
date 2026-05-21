package com.phc.movieflix.controller.docs;

import com.phc.movieflix.config.ErrorResponse;
import com.phc.movieflix.config.ValidationErrorResponse;
import com.phc.movieflix.dtos.request.StreamingRequest;
import com.phc.movieflix.dtos.request.StreamingRequestUpdate;
import com.phc.movieflix.dtos.response.StreamingResponse;
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

@Tag(name = "Streamings", description = "Gerenciamento dos serviços de streaming.")
@SecurityRequirement(name = "bearerAuth")
public interface StreamingDocs {

    @Operation(summary = "Cadastrar streaming", description = "Cria um novo serviço de streaming.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Streaming cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content)
    })
    ResponseEntity<StreamingResponse> addStreaming(StreamingRequest dto);

    @Operation(summary = "Listar streamings", description = "Retorna todos os serviços de streaming cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Streamings retornados com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content)
    })
    ResponseEntity<List<StreamingResponse>> getAllStreaming();

    @Operation(summary = "Buscar streaming por ID", description = "Retorna um serviço de streaming específico pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Streaming encontrado",
                    content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Streaming não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<StreamingResponse> getStreamingById(
            @Parameter(description = "ID do streaming.", example = "1") Long id);

    @Operation(summary = "Excluir streaming", description = "Remove um serviço de streaming pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Streaming removido com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Streaming não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Void> deleteStreamingById(
            @Parameter(description = "ID do streaming.", example = "1") Long id);

    @Operation(summary = "Atualizar streaming", description = "Atualiza os dados de um serviço de streaming pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Streaming atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = StreamingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Token JWT ausente ou inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Streaming não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<StreamingResponse> updateStreaming(
            @Parameter(description = "ID do streaming.", example = "1") Long id,
            StreamingRequestUpdate request);
}
