package com.workintech.twitterClone.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "twitter")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "location")
    private String location;

    @Column(name = "profil_picture")
    private String profilePicture;

    @Column(name = "profile_wallpaper")
    private String profileWallpaper;

    @Column(name = "liked_tweets_id")
    private List<Long> likedTweetsIdList;

    @Column(name = "retweeted_tweets_id")
    private List<Long> retweetedTweetsIdList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Tweet> tweets;

public void addLikedTweet(long id){
    if(likedTweetsIdList == null){
        likedTweetsIdList = new ArrayList<>();
    }
    if (likedTweetsIdList.contains(id)){
        throw new RuntimeException();
        // Exceptions gelecek düzeltilecek.
    }
    likedTweetsIdList.add(id);
}
public void addTweet(Tweet tweet){
    if(tweets == null){
        tweets = new ArrayList<>();
    }
    tweets.add(tweet);
}
public void removeLikedTweet(long id){
    if(likedTweetsIdList == null) {
        throw  new RuntimeException();
        /// Exceptions gelecek. Düzeltilecek
    }
    if(likedTweetsIdList.contains(id)){
        likedTweetsIdList = likedTweetsIdList.stream()
                .filter(ids -> ids != id)
                .collect(Collectors.toList());
    } else {
        throw  new RuntimeException();
        /// Exceptions. Düzeltilecek
    }
}
public void addRetweetedTweetsIdList(long id){
    if(retweetedTweetsIdList == null) {
        retweetedTweetsIdList = new ArrayList<>();
    }
    if(retweetedTweetsIdList.contains(id)){
        throw new RuntimeException();
        /// Exceptions Gelecek.!
    }
    retweetedTweetsIdList.add(id);
}
    public void removeRetweetedTweetsIdList(long id){
        if(retweetedTweetsIdList == null){
          throw new RuntimeException();
          /// Handle edilcek.
        }
        if(retweetedTweetsIdList.contains(id)){
            retweetedTweetsIdList = retweetedTweetsIdList.stream().filter(Ids->Ids !=id).collect(Collectors.toList());
            return;
        }
        throw new RuntimeException();
        /// Handle edilecek.
    }

}
