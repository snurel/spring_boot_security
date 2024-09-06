package dev.samet.security.service;

import dev.samet.security.models.AppUser;
import dev.samet.security.repository.UserRepository;
import dev.samet.security.requests.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AppUser register(LoginRequest request) {
        request.setPassword(encoder.encode(request.getPassword()));
        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        return userRepository.save(user);
    }

    public String login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(request.getUsername());
            }
            return null;
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
