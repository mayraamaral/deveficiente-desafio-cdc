package dev.mayra.seeddesafiocdc.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyInUseException extends RuntimeException {
    public AlreadyInUseException(String message) {
        super(message);
    }

    @Deprecated
    public AlreadyInUseException() {
        super(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
