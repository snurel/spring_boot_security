package dev.samet.security.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTeamRequest {
    private String name;
    private String stadiumName;
    private int stadiumCapacity;
}
