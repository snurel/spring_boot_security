package dev.samet.security.service.team;

import dev.samet.security.dto.TeamDto;
import dev.samet.security.models.Team;
import dev.samet.security.requests.AddTeamRequest;

import java.util.List;

public interface ITeamService {
    TeamDto addTeam(AddTeamRequest team);
    List<TeamDto> getTeams();
}
