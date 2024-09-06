package dev.samet.security.controller;

import dev.samet.security.models.Team;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/home")
public class HomeController {

    private static final List<Team> teams = new ArrayList<>();

    static {
        teams.add(new Team(1, "Boluspor", 12_000));
        teams.add(new Team(2, "Rizespor", 24_000));
        teams.add(new Team(3, "Amasyaspor", 8_500));
        teams.add(new Team(4, "Inegolspor", 5_000));
    }

    @GetMapping
    public String welcome() {
        return "Welcome Home Sanitarium!";
    }

    @GetMapping("/teams")
    public List<Team> getTeams(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Request made by: " + username);

        return teams;
    }

    @PostMapping("/teams")
    public Team addTeam(@RequestBody Team team){
        int id = teams.size() + 1;
        team.setId(id);
        teams.add(team);
        return team;
    }
}
