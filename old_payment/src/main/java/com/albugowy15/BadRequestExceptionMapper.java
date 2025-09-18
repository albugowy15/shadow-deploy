package com.albugowy15;

import java.util.List;
import com.albugowy15.shared.common.ApiResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException exception) {
        ApiResponse<Void> apiResponse =
                new ApiResponse<>(false, "Bad Request", null, List.of(exception.getMessage()));
        return Response.status(Response.Status.BAD_REQUEST).entity(apiResponse).build();
    }

}
