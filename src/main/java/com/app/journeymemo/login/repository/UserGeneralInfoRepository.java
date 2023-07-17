package com.app.journeymemo.login.repository;


import com.app.journeymemo.login.model.UserGeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGeneralInfoRepository extends JpaRepository<UserGeneralInfo,Long> {
}
