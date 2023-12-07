package com.workintech.twitterClone.service;

import com.workintech.twitterClone.entity.Tweet;

import java.util.List;

public interface TweetService {
List<Tweet> findAllTweets();
Tweet deleteTweet(long id);
Tweet saveTweet(Tweet tweet);
List<Tweet> findAllTweetsByFollowing(long id);
Tweet findTweetById(long id);
List<Tweet> findTweetByUserId(long id);

}
