package com.workintech.twitterClone.service;

import com.workintech.twitterClone.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface  UserService extends UserDetailsService {
    User saveUser(User user);
    User deleteUser(long id);
    Optional<User> findUserByEmail(String email);
    User findUserById(long id);
    List<User> findAllUsers();
}
