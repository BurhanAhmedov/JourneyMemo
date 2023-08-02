package com.app.journeymemo.login.mapper;

import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.model.User;
import com.app.journeymemo.login.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    @Mapping(target = "id",ignore = true)
    User mapToUserGeneralFromRequest(UserRequest userRequest);

    List<UserDto> mapToUserListDto(List<User> user);
    UserDto mapToUserDto(User userGeneralInfo);


}
