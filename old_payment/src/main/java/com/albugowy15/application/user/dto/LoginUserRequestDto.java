package com.albugowy15.application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequestDto(
        @NotBlank(message = "email cannot be empty") @Email(
                message = "email must be valid") String email,

        @NotBlank(message = "password cannot be empty") String password) {

}
