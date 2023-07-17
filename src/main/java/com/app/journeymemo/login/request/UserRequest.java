package com.app.journeymemo.login.request;

import lombok.Data;

@Data
public class UserRequest {
    private UserLoginInfoRequest userLoginInfoRequest;
    private UserGeneralInfoRequest userGeneralInfoRequest;
}
