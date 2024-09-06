package dev.samet.security.controller;

import dev.samet.security.models.AppUser;
import dev.samet.security.requests.LoginRequest;
import dev.samet.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/register")
    public AppUser register(@RequestBody LoginRequest user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest user) {
        return userService.login(user);
    }
}
