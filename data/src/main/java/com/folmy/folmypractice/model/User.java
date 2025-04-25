package com.folmy.folmypractice.model;

import com.folmy.folmypractice.enums.LevelInDevelopment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

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

    @Column(name = "level_in_development", nullable = false)
    private LevelInDevelopment levelInDevelopment;

    @Column(name = "hours_available_per_week", nullable = false)
    private Integer hoursAvailablePerWeek;

    @Column(name = "time_zone", nullable = false)
    private ZoneId timeZone;

    //Рассчитывается автоматически(Доработать)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}