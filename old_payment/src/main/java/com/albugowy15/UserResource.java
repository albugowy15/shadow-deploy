package com.albugowy15;

import java.util.List;

import com.albugowy15.dtos.RegisterUserDto;
import com.albugowy15.entities.UserEntity;
import com.albugowy15.repositories.UserRepository;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/register")
    public RegisterUserDto register(@Valid RegisterUserDto registerUserDto) {
        userRepository.create(registerUserDto.toEntity());
        return registerUserDto;
    }

    @GET
    @Path("/")
    public List<UserEntity> getAll() {
        return userRepository.listAll();
    }
}
