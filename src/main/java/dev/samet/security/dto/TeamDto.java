package dev.samet.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {
    private int id;
    private String name;
    private String stadiumName;
    private int stadiumCapacity;
}
