package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.request.UserRequest;

import java.util.List;

public interface UserService {
    void createUser(UserRequest userRequest);

    List<UserDto> getAllUser();

    UserDto getUserById(String id);
    UserDto getUserByUsername(String username);

    void updateUserById(String id, UserRequest userRequest);

    void deleteUser(String id);

    String loginUser(String username,String password);

}
