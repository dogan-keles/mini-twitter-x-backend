package com.workintech.twitterClone.controller;

import com.workintech.twitterClone.dto.LoggedinUser;
import com.workintech.twitterClone.dto.UserResponse;
import com.workintech.twitterClone.entity.User;
import com.workintech.twitterClone.service.AuthenticationService;
import com.workintech.twitterClone.service.UserService;
import com.workintech.twitterClone.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/profile")
public class UserController {
    private UserService userService;
    private AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public UserResponse register(@RequestBody User user){
        return Converter.userResponseConverter(userService.saveUser(user));
    }
    @PostMapping("/login")
    public UserResponse login(@RequestBody LoggedinUser loggedinUser){
       User user = authenticationService.login(loggedinUser);
       return Converter.userResponseConverter(user);
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteUSer(@PathVariable long id){
        return Converter.userResponseConverter(userService.deleteUser(id));
    }
}
