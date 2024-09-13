package dev.samet.security.mapper;

import dev.samet.security.dto.UserDto;
import dev.samet.security.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void testToUserDto() {
        AppUser user = AppUser.builder()
                .id(1L)
                .username("testuser")
                .build();

        UserDto userDto = userMapper.toUserDto(user);

        assertNotNull(userDto);
        assertEquals(1L, userDto.getId());
        assertEquals("testuser", userDto.getUsername());
    }
}