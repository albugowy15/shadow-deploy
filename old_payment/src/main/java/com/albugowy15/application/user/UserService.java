package com.albugowy15.application.user;

import java.util.List;
import com.albugowy15.application.user.dto.LoginUserRequestDto;
import com.albugowy15.application.user.dto.LoginUserResponseDto;
import com.albugowy15.application.user.dto.RegisterUserRequestDto;
import com.albugowy15.application.user.dto.UserResponseDto;
import com.albugowy15.domain.entity.UserEntity;
import com.albugowy15.domain.repository.UserRepository;
import com.albugowy15.shared.common.ApplicationException;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;


@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public void register(RegisterUserRequestDto request) {
        Boolean isEmailExists = userRepository.isEmailExists(request.email());
        if (isEmailExists) {
            throw new ApplicationException(Response.Status.BAD_REQUEST, "Email already exists");
        }
        try {
            userRepository.create(request.toEntity());
        } catch (Exception e) {
            throw new ApplicationException(Response.Status.INTERNAL_SERVER_ERROR,
                    "Failed to register user");
        }
    }

    public LoginUserResponseDto login(LoginUserRequestDto loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.email());
        if (user == null) {
            throw new ApplicationException(Response.Status.BAD_REQUEST, "Invalid credentials");
        }

        Boolean isPasswordValid = BcryptUtil.matches(loginRequest.password(), user.password);
        if (!isPasswordValid) {
            throw new ApplicationException(Response.Status.BAD_REQUEST, "Invalid credentials");
        }
        return new LoginUserResponseDto("thetoken", "therefresh");
    }

    public List<UserResponseDto> listAll() {
        List<UserResponseDto> users =
                this.userRepository.streamAll().map(UserResponseDto::fromEntity).toList();
        return users;
    }
}
