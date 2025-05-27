package com.folmy.folmypractice.mapper;

import com.folmy.folmypractice.dto.RegisterRequestDto;
import com.folmy.folmypractice.dto.UserDetailResponseDto;
import com.folmy.folmypractice.dto.UserSummaryResponseDto;
import com.folmy.folmypractice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(RegisterRequestDto registerRequestDto);

    List<UserSummaryResponseDto> toUserSummaryResponseDto(List<User> user);

    UserDetailResponseDto toUserDetailResponseDto(User user);
}