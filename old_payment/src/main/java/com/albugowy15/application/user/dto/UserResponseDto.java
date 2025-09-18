package com.albugowy15.application.user.dto;

import com.albugowy15.domain.entity.UserEntity;

public record UserResponseDto(String id, String fullname, String email) {
    public static UserResponseDto fromEntity(UserEntity entity) {
        return new UserResponseDto(entity.id.toString(), entity.fullname, entity.email);
    }
}
