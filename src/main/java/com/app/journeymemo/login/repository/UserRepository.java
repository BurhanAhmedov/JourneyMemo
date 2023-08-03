package com.app.journeymemo.login.repository;


import com.app.journeymemo.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select (count(u.username)) from User u where upper(u.username) = upper(?1)")
    int checkUsernameUnique(String username);

    @Query("""
            update User u set u.name = ?1, u.surname = ?2, u.birthday = ?3, u.country = ?4, u.gender = ?5,
            u.username =?6,u.email = ?7, u.password = ?8
            where u.id = ?9""")
    void updateUserById(String name, String surname, LocalDate birthday, String country, String gender,
                        String username, String email, String password, String id);
    User findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
