package com.app.journeymemo.login.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_login_info")
public class UserLoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String username;
    @Column(name = "fk_user_id")
    Long fkUserId;

}