package com.app.journeymemo.login.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_login_info")
public class UserLoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 38)
    String id;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String username;
    @Column(name = "fk_user_id", length = 38)
    String fkUserId;

}
