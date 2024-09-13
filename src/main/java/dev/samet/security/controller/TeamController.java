package dev.samet.security.controller;

import dev.samet.security.dto.TeamDto;
import dev.samet.security.models.Team;
import dev.samet.security.requests.AddTeamRequest;
import dev.samet.security.service.team.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final ITeamService teamService;

    @GetMapping("/")
    public ResponseEntity<List<TeamDto>> getTeams(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        System.out.println("Request made by: " + username);

        List<TeamDto> teams = teamService.getTeams();

        return ResponseEntity.ok(teams);
    }

    @PostMapping("/")
    public ResponseEntity<TeamDto> addTeam(@RequestBody AddTeamRequest request){
        try {
            TeamDto newTeam = teamService.addTeam(request);
            return ResponseEntity.ok(newTeam);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
