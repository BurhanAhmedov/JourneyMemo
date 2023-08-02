package com.app.journeymemo.login.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserRequest {
    String name;
    String surname;
    String email;
    String username;
    String password;
    LocalDate birthday;
    String country;
    String gender;

}
