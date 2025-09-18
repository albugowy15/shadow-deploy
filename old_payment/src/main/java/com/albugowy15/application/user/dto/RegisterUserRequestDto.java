package com.albugowy15.application.user.dto;

import com.albugowy15.domain.entity.UserEntity;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequestDto(
        @NotBlank(message = "fullname cannot be empty") @Size(min = 1, max = 255,
                message = "fullname must be between 1 to 255 characters") String fullname,

        @NotBlank(message = "email cannot be empty") @Size(min = 1, max = 255,
                message = "email must be between 1 to 255 characters") @Email(
                        message = "email must be valid") String email,

        @NotBlank(message = "password cannot be empty") @Size(min = 8, max = 16,
                message = "password must be between 8 to 16 characters") String password) {


    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.fullname = this.fullname.trim();
        userEntity.email = this.email.trim();
        userEntity.password = BcryptUtil.bcryptHash(this.password().trim());
        return userEntity;
    }
}
