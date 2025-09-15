package com.albugowy15.dtos;

import com.albugowy15.entities.UserEntity;

import jakarta.validation.constraints.*;

public record RegisterUserDto(
        @NotBlank(message = "fullname cannot be empty") String fullname,
        @NotBlank(message = "password cannot be empty") String password,
        @NotBlank(message = "email cannot be empty") String email) {

    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.fullname = this.fullname.trim();
        user.email = this.email.trim();
        user.password = this.password.trim();
        return user;
    }
}
