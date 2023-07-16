package com.app.journeymemo.login.repository;


import com.app.journeymemo.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
