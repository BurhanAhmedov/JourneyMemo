package com.app.journeymemo.login.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserRequest {
    String id;
    String name;
    String surname;
    LocalDate birthday;
    String email;
    String country;
    String username;
    String password;
    String gender;

}
