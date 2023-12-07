package com.workintech.twitterClone.service;

import com.workintech.twitterClone.entity.Tweet;
import com.workintech.twitterClone.entity.User;
import com.workintech.twitterClone.repository.TweetRepository;
import com.workintech.twitterClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImp implements TweetService {
    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetServiceImp(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Tweet> findAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet deleteTweet(long id) {
        Tweet tweet = findTweetById(id);

        if (tweet == null) {
            throw new RuntimeException("Tweet not found with ID: " + id);
            ///Handle Edilecek!
        }

        tweetRepository.delete(tweet);
        return tweet;
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        if(tweet.getTweetText() == null){
            throw new RuntimeException();
            /// Handle edilecek.
        }
        User user =  tweet.getUser();
        user.addTweet(tweet);
        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findAllTweetsByFollowing(long id) {
        return tweetRepository.findAllTweetsByFollowing(id);
    }

    @Override
    public Tweet findTweetById(long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if(optionalTweet.isPresent()){
            return optionalTweet.get();
        }
        throw new RuntimeException();
        /// Handle edilecek Düzeltilcek
    }

    @Override
    public List<Tweet> findTweetByUserId(long id) {
        List<Tweet> tweetList = tweetRepository.findTweetByUserId(id);
        if(!tweetList.isEmpty()){
            return tweetList;
        }
        throw new RuntimeException();
        /// Handle Edlecek.
    }
}
