package dev.samet.security.mapper;

import dev.samet.security.dto.TeamDto;
import dev.samet.security.models.Team;
import dev.samet.security.requests.AddTeamRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamMapperTest {
    private TeamMapper teamMapper;

    @BeforeEach
    void setUp() {
        teamMapper = new TeamMapper();
    }

    @Test
    void testToTeamDto() {
        // Create a Team object
        Team team = Team.builder()
                .id(1)
                .name("FC Example")
                .stadiumName("Example Stadium")
                .stadiumCapacity(50000)
                .build();

        // Map to TeamDto
        TeamDto teamDto = teamMapper.toTeamDto(team);

        // Assert that the fields are correctly mapped
        assertNotNull(teamDto);
        assertEquals(1, teamDto.getId());
        assertEquals("FC Example", teamDto.getName());
        assertEquals("Example Stadium", teamDto.getStadiumName());
        assertEquals(50000, teamDto.getStadiumCapacity());
    }

    @Test
    void testToTeam() {
        AddTeamRequest request = new AddTeamRequest();
        request.setName("FC Example");
        request.setStadiumName("Example Stadium");
        request.setStadiumCapacity(50000);

        // Map to Team
        Team team = teamMapper.toTeam(request);

        // Assert that the fields are correctly mapped
        assertNotNull(team);
        assertEquals("FC Example", team.getName());
        assertEquals("Example Stadium", team.getStadiumName());
        assertEquals(50000, team.getStadiumCapacity());
    }
}