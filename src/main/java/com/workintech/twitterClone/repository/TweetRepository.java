package com.workintech.twitterClone.repository;
import com.workintech.twitterClone.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT t FROM Tweet t " +
            "INNER JOIN t.user u " +
            "WHERE u.id = :id ORDER BY t.id DESC")
    public List<Tweet> findTweetByUserId(long id);

    @Query("SELECT t FROM Tweet t ORDER BY t.id DESC")
    public List<Tweet> findAllTweetsByFollowing(long id);


}
