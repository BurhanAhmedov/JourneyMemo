package com.app.journeymemo.login.mapper;

import com.app.journeymemo.login.model.User;
import com.app.journeymemo.login.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User mapToUserFromRequest(UserRequest userRequest);
}
