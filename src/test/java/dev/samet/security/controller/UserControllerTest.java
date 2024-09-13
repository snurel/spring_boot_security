package dev.samet.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.samet.security.dto.UserDto;
import dev.samet.security.requests.LoginRequest;
import dev.samet.security.service.team.ITeamService;
import dev.samet.security.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testRegister() throws Exception {
        UserDto mockUserDto = new UserDto();
        mockUserDto.setUsername("testuser");

        when(userService.register(any(LoginRequest.class))).thenReturn(mockUserDto);

        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("password");


        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"));
    }

    @Test
    void testLogin() throws Exception {
        // Mock token returned by UserService
        String mockToken = "mock-jwt-token";

        // Define the behavior of the userService.login method
        when(userService.login(any(LoginRequest.class))).thenReturn(mockToken);

        // Create test request body
        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("password");

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // Serialize the request object to JSON
                .andExpect(status().isOk()) // Verify that the HTTP status code is 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(mockToken)); // Verify that the token in the response matches the mocked token
    }
}