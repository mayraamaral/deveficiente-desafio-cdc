package dev.mayra.seeddesafiocdc.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }

    @Deprecated
    public InvalidRequestException() {
        super(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
