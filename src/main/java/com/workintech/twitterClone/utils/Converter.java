package com.workintech.twitterClone.utils;

import com.workintech.twitterClone.dto.TweetResponse;
import com.workintech.twitterClone.dto.UserResponse;
import com.workintech.twitterClone.dto.UserTweetResponse;
import com.workintech.twitterClone.entity.Tweet;
import com.workintech.twitterClone.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static String formatDate(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(dateFormatter);
    }

    public static UserResponse userResponseConverter(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getPhoneNumber(), user.getUsername(), formatDate(user.getBirthday()), formatDate(user.getRegisterDate()), user.getLocation(),
                user.getProfilePicture());
    }

    public static List<UserResponse> userResponseListConverter(List<User> userList) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : userList) {
            userResponseList.add(userResponseConverter(user));
        }
        return userResponseList;
    }

    public static UserTweetResponse userTweetResponseConverter(UserResponse userResponse) {
        return new UserTweetResponse(userResponse.id(), userResponse.firstName(), userResponse.lastName(),
                userResponse.profilePicture(), userResponse.userName());
    }

    public static List<UserTweetResponse> userTweetResponseListConverter(List<UserResponse> userResponseList) {
        List<UserTweetResponse> userTweetResponseList = new ArrayList<>();
        for (UserResponse userResponse : userResponseList) {
            userTweetResponseList.add(userTweetResponseConverter(userResponse));
        }
        return userTweetResponseList;
    }

    public static TweetResponse tweetResponseConverter(Tweet tweet){
        return new TweetResponse(tweet.getId(), tweet.getTweetText(),tweet.getCommentsIdList(),
                formatDate(tweet.getPostDate()),tweet.getLikedByUserId(),tweet.getRetweetedByUserId() ,userTweetResponseConverter(userResponseConverter(tweet.getUser())), tweet.getCommentedTo());
    }

    public static List<TweetResponse> tweetResponseListConverter(List<Tweet> tweetList){
        List<TweetResponse> tweetResponseList = new ArrayList<>();
        for(Tweet tweet : tweetList){
            tweetResponseList.add(tweetResponseConverter(tweet));
        }
        return tweetResponseList;
    }

}
