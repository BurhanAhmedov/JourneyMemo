package com.app.journeymemo.login.controller;

import com.app.journeymemo.login.request.UserRequest;
import com.app.journeymemo.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getUserGeneralInfoRequest(), userRequest.getUserLoginInfoRequest());
    }
}
