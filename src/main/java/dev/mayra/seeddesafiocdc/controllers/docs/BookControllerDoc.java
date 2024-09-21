package dev.mayra.seeddesafiocdc.controllers.docs;

import dev.mayra.seeddesafiocdc.model.book.BookMinifiedDTO;
import dev.mayra.seeddesafiocdc.model.book.BookRequestDTO;
import dev.mayra.seeddesafiocdc.model.book.BookResponseDTO;
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

public interface BookControllerDoc {
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @Operation(summary = "Create a book")
    public ResponseEntity<BookResponseDTO> create(@RequestBody @Valid BookRequestDTO book);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @Operation(summary = "List books")
    public ResponseEntity<List<BookResponseDTO>> listAll();

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK")
    })
    @Operation(summary = "List books but in a minified version")
    public ResponseEntity<List<BookMinifiedDTO>> listAllMinified();

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponseDTO.class))),
    })
    @Operation(summary = "List a book by its id")
    public ResponseEntity<BookResponseDTO> listById(@PathVariable Long id);
}
