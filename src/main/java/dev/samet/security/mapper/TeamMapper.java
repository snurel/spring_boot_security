package dev.samet.security.mapper;

import dev.samet.security.dto.TeamDto;
import dev.samet.security.models.Team;
import dev.samet.security.requests.AddTeamRequest;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamDto toTeamDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .stadiumCapacity(team.getStadiumCapacity())
                .stadiumName(team.getStadiumName())
                .build();
    }

    public Team toTeam(AddTeamRequest request) {
        return Team.builder()
                .name(request.getName())
                .stadiumName(request.getStadiumName())
                .stadiumCapacity(request.getStadiumCapacity())
                .build();
    }
}
