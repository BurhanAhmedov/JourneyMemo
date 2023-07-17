package com.app.journeymemo.login.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserGeneralInfoDto {
    String name;
    String surname;
    LocalDate birthday;
    String email;
    String country;
    String username;
    String  gender;
}
