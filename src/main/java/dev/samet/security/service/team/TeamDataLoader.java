package dev.samet.security.service.team;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.samet.security.models.Team;
import dev.samet.security.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamDataLoader {

    private final TeamRepository teamRepository;
    private final ObjectMapper objectMapper;

//    @PostConstruct
    public void loadTeamData() throws IOException {
        ClassPathResource resource = new ClassPathResource("teams.json");
        InputStream inputStream = resource.getInputStream();

        List<Team> teams = objectMapper.readValue(inputStream, new TypeReference<>() {});

        for (Team team : teams) {
            if (!teamRepository.existsByName(team.getName())) {
                teamRepository.save(team);
            }
        }
    }
}