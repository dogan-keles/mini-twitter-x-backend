package com.workintech.twitterClone.controller;

import com.workintech.twitterClone.dto.TweetResponse;
import com.workintech.twitterClone.entity.Tweet;
import com.workintech.twitterClone.entity.User;
import com.workintech.twitterClone.service.TweetService;
import com.workintech.twitterClone.service.UserService;
import com.workintech.twitterClone.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {
    private UserService userService;
    private TweetService tweetService;

    @Autowired
    public TweetController(UserService userService, TweetService tweetService) {
        this.userService = userService;
        this.tweetService = tweetService;
    }
    @GetMapping("/home/{id}")
    public List<TweetResponse> findAllTweetsByFollowing(@PathVariable long id){
        return Converter.tweetResponseListConverter(tweetService.findAllTweetsByFollowing(id));
    }
    @GetMapping("/profile/{id}")
    public List<TweetResponse> findAllTweetsByUserId(@PathVariable long id){
        return Converter.tweetResponseListConverter(tweetService.findTweetByUserId(id));
    }
    @GetMapping("/{id}")
    public TweetResponse findTweetById(@PathVariable long id){
        return Converter.tweetResponseConverter(tweetService.findTweetById(id));
    }
     @PostMapping("/")
    public TweetResponse saveTweet(@RequestBody Tweet tweet){
         User user = userService.findUserById(tweet.getUser().getId());
         tweet.setCommentedTo(0L);
         tweet.setUser(user);
         return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
     }
    @PutMapping("/{id}")
    public TweetResponse updateTweet(@RequestBody Tweet tweet, @PathVariable long  id){
        User user = userService.findUserById(tweet.getUser().getId());
        tweet.setId(id);
        tweet.setUser(user);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }
    @DeleteMapping("/{id}")
    public TweetResponse deleteTweet( @PathVariable long id){
        Tweet tweet = tweetService.findTweetById(id);

        if(tweet.getCommentsIdList() == null ){
            tweet.setCommentsIdList(new ArrayList<>());
            tweet.setCommentedTo(0L);
            tweetService.saveTweet(tweet);
        }

        if( !(tweet.getCommentsIdList().isEmpty())){

            for(long twt : tweet.getCommentsIdList()){
                Tweet foundTwt = tweetService.findTweetById(twt);
                foundTwt.setCommentedTo(0L);
                tweetService.saveTweet(foundTwt);

            }
            tweet.setCommentsIdList(new ArrayList<>());
            tweet.setCommentedTo(0L);
            tweetService.saveTweet(tweet);
        }
        if(tweet.getCommentedTo() != 0){
            Tweet foundTweet = tweetService.findTweetById(tweet.getCommentedTo());
            foundTweet.removeCommentsIdList(id);
        }
        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }
    @PostMapping("/like/{id}")
    public TweetResponse likeTweet( @PathVariable long id,@RequestBody User requestBodyUser){
        Tweet tweet = tweetService.findTweetById(id);
        User loggedInUser = userService.findUserById(requestBodyUser.getId());
        requestBodyUser.addLikedTweet(tweet.getId());
        tweet.addLikedByUserId(requestBodyUser.getId());
        tweetService.saveTweet(tweet);
        return Converter.tweetResponseConverter(tweet);
    }

    @PostMapping("/dislike/{id}")
    public TweetResponse dislike(@PathVariable long id, @RequestBody User loggedInUser) {
        Tweet tweet = tweetService.findTweetById(id);
        User userToDislike = userService.findUserById(loggedInUser.getId());
        tweet.removeLikedByUserId(userToDislike.getId());
        userToDislike.removeLikedTweet(tweet.getId());
        tweetService.saveTweet(tweet);
        return Converter.tweetResponseConverter(tweet);
    }
    @PostMapping("/retweet/{id}")
    public TweetResponse retweetTweet(@PathVariable long id, @RequestBody User loggedInUser) {
        Tweet tweetToRetweet = tweetService.findTweetById(id);
        User userRetweeting = userService.findUserById(loggedInUser.getId());
        userRetweeting.addRetweetedTweetsIdList(tweetToRetweet.getId());
        tweetToRetweet.addRetweetedByUserId(userRetweeting.getId());
        tweetService.saveTweet(tweetToRetweet);
        return Converter.tweetResponseConverter(tweetToRetweet);
    }
    @PostMapping("/unretweet/{id}")
    public TweetResponse unRetweet(@PathVariable long id, @RequestBody User loggedInUser) {
        Tweet tweetToUnRetweet = tweetService.findTweetById(id);
        User userUnRetweeting = userService.findUserById(loggedInUser.getId());
        tweetToUnRetweet.removeRetweetedByUserId(userUnRetweeting.getId());
        userUnRetweeting.removeRetweetedTweetsIdList(tweetToUnRetweet.getId());
        tweetService.saveTweet(tweetToUnRetweet);
        return Converter.tweetResponseConverter(tweetToUnRetweet);
    }



}
