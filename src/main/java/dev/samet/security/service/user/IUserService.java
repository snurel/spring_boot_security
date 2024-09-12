package dev.samet.security.service.user;

import dev.samet.security.dto.UserDto;
import dev.samet.security.models.AppUser;
import dev.samet.security.requests.LoginRequest;

public interface IUserService {
    UserDto register(LoginRequest request);
    String login(LoginRequest request);
}
