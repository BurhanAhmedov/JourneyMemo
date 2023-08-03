package com.app.journeymemo.login.service;


import com.app.journeymemo.login.dto.UserDto;
import com.app.journeymemo.login.request.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> createUser(UserRequest userRequest);

    List<UserDto> getAllUser();

    ResponseEntity<UserDto> getUserById(String id);
    ResponseEntity<UserDto> getUserByUsername(String username);

    void updateUserById(String id, UserRequest userRequest);

    void deleteUser(String id);

    ResponseEntity<String> loginUser(String username, String password);

}
