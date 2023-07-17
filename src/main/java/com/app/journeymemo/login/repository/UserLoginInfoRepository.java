package com.app.journeymemo.login.repository;

import com.app.journeymemo.login.model.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo,Long> {

}
