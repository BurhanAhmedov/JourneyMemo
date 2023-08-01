package com.app.journeymemo.login.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_general_info")
public class UserGeneralInfo {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    UUID id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String surname;
    @Column(nullable = false)
    LocalDate birthday;
    @Column(nullable = false)
    String country;
    @Column(nullable = false)
    String gender;
}
