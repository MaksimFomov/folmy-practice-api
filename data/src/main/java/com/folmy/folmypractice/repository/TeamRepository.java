package com.folmy.folmypractice.repository;

import com.folmy.folmypractice.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    @Query("SELECT MAX(t.code) FROM Team t")
    Optional<String> findMaxTeamCode();

    boolean existsByName(String teamName);
}
