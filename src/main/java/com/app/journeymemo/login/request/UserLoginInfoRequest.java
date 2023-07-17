package com.app.journeymemo.login.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserLoginInfoRequest {
    String id;
    String email;
    String username;
    String password;
    Long fkUserId;
}
