package dev.samet.security.service.team;

import dev.samet.security.dto.TeamDto;
import dev.samet.security.mapper.TeamMapper;
import dev.samet.security.models.Team;
import dev.samet.security.repository.TeamRepository;
import dev.samet.security.requests.AddTeamRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public List<TeamDto> getTeams() {
        List<Team> allTeams = teamRepository.findAll();
        return allTeams.stream().map(this::toTeamDto).toList();
    }

    @Override
    public TeamDto addTeam(AddTeamRequest request) throws RuntimeException {
        try {
            if(request == null) {
                throw new IllegalArgumentException("AddTeamRequest cannot be null");
            }

            Team team = toTeam(request);
            Team saved = teamRepository.save(team);
            return toTeamDto(saved);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TeamDto toTeamDto(Team team) {
        return teamMapper.toTeamDto(team);
    }

    private Team toTeam(AddTeamRequest request) {
        return teamMapper.toTeam(request);
    }

}
