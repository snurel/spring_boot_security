package dev.samet.security.mapper;

import dev.samet.security.dto.UserDto;
import dev.samet.security.models.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(AppUser user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
