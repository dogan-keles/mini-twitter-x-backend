package com.workintech.twitterClone.service;

import com.workintech.twitterClone.dto.LoggedinUser;
import com.workintech.twitterClone.entity.User;
import com.workintech.twitterClone.exceptions.TwitterException;
import com.workintech.twitterClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User register(User user){
        Optional<User> foundUser = userRepository.findUserByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new TwitterException("A user with given email address already exists. Please log in!", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User createRegisteredUser = new User();
        createRegisteredUser.setFirstName(user.getFirstName());
        createRegisteredUser.setLastName(user.getLastName());
        createRegisteredUser.setPassword(encodedPassword);
        createRegisteredUser.setBirthday(user.getBirthday());
        createRegisteredUser.setLocation(user.getLocation());
        createRegisteredUser.setEmail(user.getEmail());
        createRegisteredUser.setUserName(user.getUsername());
        createRegisteredUser.setProfilePicture(user.getProfilePicture());
        createRegisteredUser.setProfileWallpaper(user.getProfileWallpaper());
        createRegisteredUser.setRegisterDate(user.getRegisterDate());
        createRegisteredUser.setPhoneNumber(user.getPhoneNumber());
        return createRegisteredUser;
    }
    public User login(LoggedinUser loginUser){
        Optional<User> optionalUser = userRepository.findUserByEmail(loginUser.email());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            System.out.println(user.getPassword() + loginUser.password());
            boolean isPasswordSame = passwordEncoder.matches(loginUser.password(),user.getPassword());
            if(isPasswordSame){
                return user;
            }
            throw new TwitterException("Invalid Credantials", HttpStatus.BAD_REQUEST);
        }
        throw new TwitterException("Invalid Credantials", HttpStatus.BAD_REQUEST);
    }
}
