package com.folmy.folmypractice.repository;

import com.folmy.folmypractice.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    Optional<String> findFirstByOrderByNumberDesc();

    boolean existsByName(String teamName);
}
