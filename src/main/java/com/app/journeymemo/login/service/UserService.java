package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserGeneralInfoDto;
import com.app.journeymemo.login.request.UserGeneralInfoRequest;
import com.app.journeymemo.login.request.UserLoginInfoRequest;

import java.util.List;

public interface UserService {
    void createUser(UserGeneralInfoRequest userGeneralInfoRequest, UserLoginInfoRequest userLoginInfoRequest);
    List<UserGeneralInfoDto> getAllUser();
    UserGeneralInfoDto getUserById(Long id);
    void updateUser(Long id, UserGeneralInfoRequest userGeneralInfoRequest);
    void deleteUser(Long id);


}
