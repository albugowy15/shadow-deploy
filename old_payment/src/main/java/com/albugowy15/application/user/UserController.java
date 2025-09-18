package com.albugowy15.application.user;

import java.util.List;
import com.albugowy15.application.user.dto.LoginUserRequestDto;
import com.albugowy15.application.user.dto.RegisterUserRequestDto;
import com.albugowy15.application.user.dto.UserResponseDto;
import com.albugowy15.shared.common.ApiResponse;
import com.albugowy15.shared.common.ApplicationException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Path("/register")
    public Response register(@Valid RegisterUserRequestDto request) {
        try {
            userService.register(request);
            return ApiResponse.success("User registered successfully")
                    .toResponse(Response.Status.CREATED);
        } catch (Exception e) {
            if (e instanceof ApplicationException appEx) {
                return appEx.toResponse();
            }
            return ApiResponse.internalError();
        }
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginUserRequestDto request) {
        try {
            return userService.login(request).toResponse();
        } catch (Exception e) {
            if (e instanceof ApplicationException appEx) {
                return appEx.toResponse();
            }
            return ApiResponse.internalError();
        }
    }

    @GET
    public Response listAll() {
        try {
            List<UserResponseDto> users = userService.listAll();
            return ApiResponse.success(users).toResponse(Response.Status.OK);
        } catch (Exception e) {
            if (e instanceof ApplicationException appEx) {
                return appEx.toResponse();
            }
            return ApiResponse.internalError();
        }
    }

}
