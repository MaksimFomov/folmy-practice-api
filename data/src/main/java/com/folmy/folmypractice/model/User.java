package com.folmy.folmypractice.model;

import com.folmy.folmypractice.enums.LevelInDevelopment;
import com.folmy.folmypractice.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column( name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "about_me")
    private String aboutMe;

    @ElementCollection
    @CollectionTable(
            name = "user_directions_in_development",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "directions_in_development", nullable = false)
    private List<String> directionsInDevelopment;

    @ElementCollection
    @CollectionTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "skills", nullable = false)
    private List<String> skills;

    @Column(name = "level_in_development", nullable = false)
    private LevelInDevelopment levelInDevelopment;

    @Column(name = "hours_available_per_week", nullable = false)
    private Integer hoursAvailablePerWeek;

    @Column(name = "time_zone", nullable = false)
    private ZoneId timeZone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "members")
    private List<Team> teams;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}