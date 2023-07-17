package com.app.journeymemo.login.mapper;

import com.app.journeymemo.login.model.UserGeneralInfo;
import com.app.journeymemo.login.model.UserLoginInfo;
import com.app.journeymemo.login.request.UserGeneralInfoRequest;
import com.app.journeymemo.login.request.UserLoginInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserGeneralInfo mapToUserGeneralFromRequest(UserGeneralInfoRequest userGeneralInfoRequest);
    UserLoginInfo mapToUserLoginFromRequest(UserLoginInfoRequest userLoginInfoRequest);
}
