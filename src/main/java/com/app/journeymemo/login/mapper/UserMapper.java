package com.app.journeymemo.login.mapper;

import com.app.journeymemo.login.dto.UserGeneralInfoDto;
import com.app.journeymemo.login.model.UserGeneralInfo;
import com.app.journeymemo.login.model.UserLoginInfo;
import com.app.journeymemo.login.request.UserGeneralInfoRequest;
import com.app.journeymemo.login.request.UserLoginInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    @Mapping(target = "id",ignore = true)
    UserGeneralInfo mapToUserGeneralFromRequest(UserGeneralInfoRequest userGeneralInfoRequest);
    @Mapping(target = "fkUserId",ignore = true)
    @Mapping(target = "id",ignore = true)
    UserLoginInfo mapToUserLoginFromRequest(UserLoginInfoRequest userLoginInfoRequest);

    List<UserGeneralInfoDto> mapToUserListDto(List<UserGeneralInfo> userGeneralInfo);
    UserGeneralInfoDto mapToUserDto(Optional<UserGeneralInfo> userGeneralInfo);


}
