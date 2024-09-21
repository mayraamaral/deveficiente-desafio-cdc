package dev.mayra.seeddesafiocdc.controllers.docs;

import dev.mayra.seeddesafiocdc.model.category.CategoryRequestDTO;
import dev.mayra.seeddesafiocdc.model.category.CategoryResponseDTO;
import dev.mayra.seeddesafiocdc.utils.errors.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoryControllerDoc {
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @Operation(summary = "Create a category")
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO category);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @Operation(summary = "List categories")
    public ResponseEntity<List<CategoryResponseDTO>> findAll();
}
