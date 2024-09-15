package dev.mayra.seeddesafiocdc.utils.errors;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponseDTO {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errors;

    public ErrorResponseDTO(int status, String error, String message, String path, Map<String, String> errors) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
