package com.app.journeymemo.login.repository;


import com.app.journeymemo.login.model.UserGeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface UserGeneralInfoRepository extends JpaRepository<UserGeneralInfo,Long> {
    @Transactional
    @Modifying
    @Query("""
            update UserGeneralInfo u set u.name = ?1, u.surname = ?2, u.birthday = ?3, u.country = ?4, u.gender = ?5
            where u.id = ?6""")
    void updateGeneralInfoById(String name, String surname, LocalDate birthday, String country, String gender, Long id);
}
