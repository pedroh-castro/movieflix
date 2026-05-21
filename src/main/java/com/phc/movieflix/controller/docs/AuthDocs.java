package com.phc.movieflix.controller.docs;

import com.phc.movieflix.config.ErrorResponse;
import com.phc.movieflix.config.ValidationErrorResponse;
import com.phc.movieflix.dtos.request.LoginRequest;
import com.phc.movieflix.dtos.request.UserRequest;
import com.phc.movieflix.dtos.response.LoginResponse;
import com.phc.movieflix.dtos.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Cadastro de usuários e geração de token JWT.")
public interface AuthDocs {

    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário para acesso à API.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
    })
    ResponseEntity<UserResponse> register(UserRequest request);

    @Operation(summary = "Autenticar usuário", description = "Valida as credenciais e retorna um token JWT.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Usuário ou senha inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<LoginResponse> login(LoginRequest request);
}
