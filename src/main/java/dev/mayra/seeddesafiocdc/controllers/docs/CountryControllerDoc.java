package dev.mayra.seeddesafiocdc.controllers.docs;

import dev.mayra.seeddesafiocdc.model.country.CountryRequestDTO;
import dev.mayra.seeddesafiocdc.model.country.CountryResponseDTO;
import dev.mayra.seeddesafiocdc.model.country.CountryWithStatesResponseDTO;
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

public interface CountryControllerDoc {
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @Operation(summary = "Create a country")
    public ResponseEntity<CountryResponseDTO> create(@RequestBody @Valid CountryRequestDTO country);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @Operation(summary = "List countries")
    public ResponseEntity<List<CountryResponseDTO>> listAll();

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @Operation(summary = "List countries with their states")
    public ResponseEntity<List<CountryWithStatesResponseDTO>> listAllWithStates();
}
