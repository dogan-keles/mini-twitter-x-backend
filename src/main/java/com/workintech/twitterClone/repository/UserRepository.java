package com.workintech.twitterClone.repository;

import com.workintech.twitterClone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT u.email FROM User u WHERE u.email = :email")
    String emailCheck(@Param("email") String email);

    @Query("SELECT u.phoneNumber FROM User u WHERE u.phoneNumber = :phone")
    String phoneCheck(@Param("phone") String phoneNumber);

    @Query("SELECT u.userName FROM User u WHERE u.userName = :username")
    String usernameCheck(@Param("username") String username);

}
