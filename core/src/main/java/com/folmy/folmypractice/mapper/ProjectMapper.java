package com.folmy.folmypractice.mapper;

import com.folmy.folmypractice.dto.ProjectDetailResponseDto;
import com.folmy.folmypractice.dto.ProjectRequestDto;
import com.folmy.folmypractice.dto.ProjectSummaryResponseDto;
import com.folmy.folmypractice.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {
    Project toEntity(ProjectRequestDto projectRequestDto);

    ProjectSummaryResponseDto toProjectSummaryResponseDto(Project project);

    List<ProjectSummaryResponseDto> toProjectSummaryResponseDto(Collection<Project> project);

    ProjectDetailResponseDto toProjectDetailResponseDto(Project project);
}