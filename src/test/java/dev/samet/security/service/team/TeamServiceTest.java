package dev.samet.security.service.team;

import dev.samet.security.dto.TeamDto;
import dev.samet.security.mapper.TeamMapper;
import dev.samet.security.models.Team;
import dev.samet.security.repository.TeamRepository;
import dev.samet.security.requests.AddTeamRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldAddTeamInsertsAndReturnsTeamDto(){
        AddTeamRequest addTeamRequest = new AddTeamRequest("team1", "stadium1", 1000);

        Team team = new Team();  // Mocked Team entity
        team.setName(addTeamRequest.getName());
        team.setStadiumName(addTeamRequest.getStadiumName());
        team.setStadiumCapacity(10000);

        Team savedTeam = Team.builder()
                .id(1)
                .name(addTeamRequest.getName())
                .stadiumCapacity(addTeamRequest.getStadiumCapacity())
                .stadiumName(addTeamRequest.getStadiumName())
                .build();

        TeamDto teamDto = TeamDto.builder()
                .id(savedTeam.getId())
                .name(savedTeam.getName())
                .stadiumName(savedTeam.getStadiumName())
                .stadiumCapacity(savedTeam.getStadiumCapacity())
                .build();

        when(teamMapper.toTeam(addTeamRequest)).thenReturn(team);
        when(teamRepository.save(any(Team.class))).thenReturn(savedTeam);
        when(teamMapper.toTeamDto(savedTeam)).thenReturn(teamDto);

        TeamDto result = teamService.addTeam(addTeamRequest);
        // Assert
        assertNotNull(result);
        assertEquals("team1", result.getName());
        assertEquals("stadium1", result.getStadiumName());
        assertEquals(1000, result.getStadiumCapacity());
        verify(teamRepository).save(team);
    }

    @Test
    void testAddTeamThrowsIllegalArgumentException() {
        // Arrange
        AddTeamRequest request = new AddTeamRequest("team1", "stadium1", 10000);

        when(teamMapper.toTeam(request)).thenThrow(new IllegalArgumentException("Invalid team data"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> teamService.addTeam(request));
        verify(teamRepository, never()).save(any(Team.class));
    }
}