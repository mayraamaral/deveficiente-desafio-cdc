package dev.mayra.seeddesafiocdc.controllers.docs;

import dev.mayra.seeddesafiocdc.model.state.StateRequestDTO;
import dev.mayra.seeddesafiocdc.model.state.StateWithCountryResponseDTO;
import dev.mayra.seeddesafiocdc.utils.errors.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StateControllerDoc {
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @Operation(summary = "Create a state")
    public ResponseEntity<StateWithCountryResponseDTO> create(@RequestBody @Valid StateRequestDTO state);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @Operation(summary = "List states")
    public ResponseEntity<List<StateWithCountryResponseDTO>> listAll();

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @Operation(summary = "List all states from a country")
    public ResponseEntity<List<StateWithCountryResponseDTO>> listAllByCountryId(@PathVariable Long countryId);

}
