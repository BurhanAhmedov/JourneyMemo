package com.app.journeymemo.login.mapper;

import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.model.UserModel;
import com.app.journeymemo.login.request.UserRequest;
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
    UserModel mapToUserGeneralFromRequest(UserRequest userRequest);

    List<UserDto> mapToUserListDto(List<UserModel> userModel);
    UserDto mapToUserDto(Optional<UserModel> userGeneralInfo);


}
