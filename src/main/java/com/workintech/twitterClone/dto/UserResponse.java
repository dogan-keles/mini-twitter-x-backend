package com.workintech.twitterClone.dto;

public record UserResponse(Long id, String firstName, String lastName, String email, String password, String phoneNumber, String userName, String birthday, String registerDate, String location, String profilePicture ) {
}
