package com.folmy.folmypractice.mapper;

import com.folmy.folmypractice.dto.TeamDetailResponseDto;
import com.folmy.folmypractice.dto.TeamRequestDto;
import com.folmy.folmypractice.dto.TeamSummaryResponseDto;
import com.folmy.folmypractice.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamMapper {
    Team toEntity(TeamRequestDto teamRequestDto);

    @Mapping(source = "project.id", target = "projectId")
    TeamSummaryResponseDto toTeamSummaryResponseDto(Team team);

    List<TeamSummaryResponseDto> toTeamSummaryResponseDto(Collection<Team> team);

    TeamDetailResponseDto toTeamDetailResponseDto(Team team);
}