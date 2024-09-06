package dev.samet.security.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String username;
    private String password;
}
