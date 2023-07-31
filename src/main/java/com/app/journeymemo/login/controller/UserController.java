package com.app.journeymemo.login.controller;

import com.app.journeymemo.login.dto.UserGeneralInfoDto;
import com.app.journeymemo.login.request.UserGeneralInfoRequest;
import com.app.journeymemo.login.request.UserLoginInfoRequest;
import com.app.journeymemo.login.request.UserRequest;
import com.app.journeymemo.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getUserGeneralInfoRequest(), userRequest.getUserLoginInfoRequest());
    }

    @GetMapping()
    public ResponseEntity<List<UserGeneralInfoDto>> getAllUserGeneralInfo() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGeneralInfoDto> getUserGeneralInfoById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/general/{id}")
    public void updateUserGeneralInfo( @PathVariable Long id,@RequestBody UserGeneralInfoRequest generalInfoRequest) {
        userService.updateGeneralInfoUser(id,generalInfoRequest);

    }

    @PutMapping("/login/{id}")
    public void updateUserLoginInfo(@PathVariable Long id,@RequestBody UserLoginInfoRequest loginInfoRequest) {
        userService.updateLoginInfoUser(id,loginInfoRequest);

    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
