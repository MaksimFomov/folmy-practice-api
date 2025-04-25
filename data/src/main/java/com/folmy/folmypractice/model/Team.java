package com.folmy.folmypractice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "team_name")
    private String teamName;

    //Рассчитывается автоматически(Доработать)
    @Column(name = "team_number", nullable = false)
    private String teamNumber;

    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;

    //Рассчитывается автоматически(Доработать)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}