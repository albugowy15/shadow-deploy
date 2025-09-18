package com.albugowy15.shared.common;

import java.util.List;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ApplicationException extends RuntimeException {
    private Status status;
    private List<String> errors;

    public ApplicationException(Status status, String message, List<String> errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }

    public ApplicationException(Status status, String message) {
        super(message);
        this.status = status;
        this.errors = null;
    }

    public ApplicationException(Status status) {
        super(status.getReasonPhrase());
        this.status = status;
        this.errors = null;
    }

    public ApplicationException(String message) {
        super(message);
        this.status = Status.INTERNAL_SERVER_ERROR;
    }

    public ApplicationException() {
        super("Internal Server Error");
        this.status = Status.INTERNAL_SERVER_ERROR;
        this.errors = null;
    }

    public Status getStatus() {
        return this.status;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public Response toResponse() {
        return ApiResponse.error(this.getMessage(), this.getErrors()).toResponse(this.getStatus());
    }
}
