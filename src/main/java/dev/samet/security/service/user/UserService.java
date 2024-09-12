package dev.samet.security.service.user;

import dev.samet.security.dto.UserDto;
import dev.samet.security.mapper.UserMapper;
import dev.samet.security.models.AppUser;
import dev.samet.security.repository.UserRepository;
import dev.samet.security.requests.LoginRequest;
import dev.samet.security.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserMapper userMapper;

    @Override
    public UserDto register(LoginRequest request) {
        request.setPassword(encoder.encode(request.getPassword()));
        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        AppUser appUser = userRepository.save(user);
        return userMapper.toUserDto(appUser);
    }

    @Override
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
