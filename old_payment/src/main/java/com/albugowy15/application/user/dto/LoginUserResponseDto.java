package com.albugowy15.application.user.dto;

import com.albugowy15.shared.common.ApiResponse;
import jakarta.ws.rs.core.Response;

public record LoginUserResponseDto(String accessToken, String refreshToken) {
    public Response toResponse() {
        return ApiResponse.success("Login successful", this).toResponse(Response.Status.OK);
    }
}
