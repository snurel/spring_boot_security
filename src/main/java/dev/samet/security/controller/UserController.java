package dev.samet.security.controller;

import dev.samet.security.dto.UserDto;
import dev.samet.security.models.AppUser;
import dev.samet.security.requests.LoginRequest;
import dev.samet.security.response.LoginResponse;
import dev.samet.security.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody LoginRequest user) {
        UserDto dto = userService.register(user);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest user) {
        String token = userService.login(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
