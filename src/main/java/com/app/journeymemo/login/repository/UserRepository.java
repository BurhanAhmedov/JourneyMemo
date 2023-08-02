package com.app.journeymemo.login.repository;


import com.app.journeymemo.login.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("select (count(u.username)) from UserModel u where upper(u.username) = upper(?1)")
    int checkUsernameUnique(String username);

    @Query("""
            update UserModel u set u.name = ?1, u.surname = ?2, u.birthday = ?3, u.country = ?4, u.gender = ?5,
            u.username =?6,u.email = ?7, u.password = ?8
            where u.id = ?9""")
    void updateUserById(String name, String surname, LocalDate birthday, String country, String gender,
                        String username, String email, String password, Long id);
}
