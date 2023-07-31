package com.app.journeymemo.login.repository;

import com.app.journeymemo.login.model.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo,Long> {
    @Transactional
    @Modifying
    @Query("delete from UserLoginInfo u where u.fkUserId = ?1")
    void deleteByFkId(Long fkUserId);
    @Query("select (count(u.username)) from UserLoginInfo u where upper(u.username) = upper(?1)")
    int checkUsernameUnique(String username);
    @Transactional
    @Modifying
    @Query("update UserLoginInfo u set u.email = ?1, u.password = ?2, u.username = ?3 where u.id = ?4")
    int updateLoginInfoById(String email, String password, String username, Long id);

}
