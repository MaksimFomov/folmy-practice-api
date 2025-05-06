package com.folmy.folmypractice.repository;

import com.folmy.folmypractice.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    @Query("SELECT MAX(t.teamNumber) FROM Team t")
    String findMaxTeamNumber();
}
