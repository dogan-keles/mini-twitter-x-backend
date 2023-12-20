package com.workintech.twitterClone.service;

import com.workintech.twitterClone.entity.User;
import com.workintech.twitterClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        String email = userRepository.emailCheck(user.getEmail());
        String phone = userRepository.phoneCheck(user.getPhoneNumber());
        String username = userRepository.usernameCheck(user.getUsername());
        if (email != null) {
            throw new RuntimeException();
/// Handle Edilecek.
        }
        if (phone != null) {
            throw new RuntimeException();
/// Handle Edilecek.
        }
        if (username != null) {
            throw new RuntimeException();
/// Handle Edilecek.
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(long id) {
        User user = findUserById(id);
        userRepository.delete(user);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException();
            /// Handle Edlecek.
        }
        return optionalUser;
    }

    @Override
    public User findUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new RuntimeException();
        /// Handle edilecek.
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("User credentials are not valid");
                });
    }
}
