package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.request.UserRequest;

import java.util.List;

public interface UserService {
    void createUser(UserRequest userRequest);
    List<UserDto> getAllUser();
    UserDto getUserById(Long id);
    void updateUser(Long id,UserRequest userRequest);
    void deleteUser(Long id);


}
