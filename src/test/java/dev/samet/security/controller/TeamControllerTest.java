package dev.samet.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.samet.security.dto.TeamDto;
import dev.samet.security.service.team.ITeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class TeamControllerTest {

    @InjectMocks
    private TeamController teamController;

    @Mock
    private ITeamService teamService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
    }

    @Test
    void shouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        SecurityContextHolder.clearContext();

        mockMvc.perform(MockMvcRequestBuilders.get("/teams/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnTeamsWhenAuthenticated() throws Exception {

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");
        SecurityContextHolder.setContext(securityContext);

        List<TeamDto> mockTeams = List.of(
                new TeamDto(1, "team1", "stadium1", 10),
                new TeamDto(2, "team2", "stadium2", 12),
                new TeamDto(3, "team3", "stadium3", 14)
        );
        when(teamService.getTeams()).thenReturn(mockTeams);

        mockMvc.perform(MockMvcRequestBuilders.get("/teams/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("team1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("team2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("team3"));
    }
}