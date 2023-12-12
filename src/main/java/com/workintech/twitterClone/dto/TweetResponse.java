package com.workintech.twitterClone.dto;

import java.util.List;

public record TweetResponse(Long id, String tweetText, List<Long> commentsIdList, String postDate, List<Long> likedByUserId, List<Long> retweetedByUserId, UserTweetResponse userTweetResponse, Long commentedTo) {
}
