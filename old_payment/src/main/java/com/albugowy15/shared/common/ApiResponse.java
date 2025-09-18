package com.albugowy15.shared.common;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(Boolean success, String message, T data, List<String> errors) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Success", data, null);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null, null);
    }

    public static <T> ApiResponse<T> error(String message, List<String> errors) {
        return new ApiResponse<>(false, message, null, errors);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, null);
    }

    public static <T> ApiResponse<T> error(List<String> errors) {
        return new ApiResponse<>(false, "Failed", null, null);
    }

    public Response toResponse(Status status) {
        return Response.status(status).entity(this).build();
    }

    public static Response internalError(String message) {
        return new ApiResponse<>(false, message, null, null)
                .toResponse(Response.Status.INTERNAL_SERVER_ERROR);
    }

    public static Response internalError() {
        return new ApiResponse<>(false, "Internal Error", null, null)
                .toResponse(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
