package com.workintech.twitterClone.controller;

import com.workintech.twitterClone.dto.LoggedinUser;
import com.workintech.twitterClone.dto.UserResponse;
import com.workintech.twitterClone.entity.User;
import com.workintech.twitterClone.service.UserService;
import com.workintech.twitterClone.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody User user){
        return Converter.userResponseConverter(userService.saveUser(user));
    }
    @PostMapping("/login")
    public UserResponse login(@RequestBody LoggedinUser loggedinUser){
        /// TODO
        return null;
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteUSer(@PathVariable long id){
        return Converter.userResponseConverter(userService.deleteUser(id));
    }
}
