package com.workintech.twitterClone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tweet", schema = "twitter")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "tweet_text")
    private String tweetText;

    @Column(name = "post_date")
    private LocalDate postDate;

    @Column(name = "liked_by_user_id")
    private List<Long> likedByUserId;

    @Column(name = "retweeted_by_user_id")
    private List<Long> retweetedByUserId;

    @Column(name = "commented_by_user_id")
    private List<Long> commentedByUserId;

    @Column(name = "comments_list_id")
    private List<Long> commentsIdList;

    @Column(name = "commented_to_id")
    private Long commentedTo;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;


    public void removeCommentsIdList(long id){
        if(commentsIdList == null){
            throw new RuntimeException();
            /// Burası handle edilecek.
        }
        if(commentsIdList.contains(id)){
            commentsIdList = commentsIdList.stream()
                    .filter(Ids -> Ids != id).collect(Collectors.toList());
            return;
        }
        throw new RuntimeException();
        /// Buralar handle edilecek.
    }
    public void addRetweetedByUserId(long id){
        if (retweetedByUserId == null){
            retweetedByUserId = new ArrayList<>();
        }
        if (retweetedByUserId.contains(id)){
            throw new RuntimeException();

        }
        retweetedByUserId.add(id);
        /// Handle edilecek.
    }
    public void removeRetweetedByUserId(long id){
        if(retweetedByUserId == null){
            throw new RuntimeException();
            /// Handle edilecek
        }
        if(retweetedByUserId.contains(id)){
            retweetedByUserId = retweetedByUserId.stream()
                    .filter(Ids -> Ids != id).collect(Collectors.toList());
            return;
        }
        throw new RuntimeException();
        /// Handle edilecek.
    }
    public void addLikedByUserId(long id){
        if(likedByUserId == null){
            likedByUserId = new ArrayList<>();
        }
        if (likedByUserId.contains(id)){
            throw new RuntimeException();
            /// Handle Edilcek
        }
        likedByUserId.add(id);
    }
    public void removeLikedByUserId(long id){
        if(likedByUserId == null){
            throw new RuntimeException();
            /// Handle edilecek.
        }
        if(likedByUserId.contains(id)){
            likedByUserId = likedByUserId.stream()
                    .filter(Ids -> Ids != id).collect(Collectors.toList());
            return;
        }
        throw new RuntimeException();
        /// Handle edilecek.
    }
    public void addCommentsTweetIdList(long id){
        if(commentedByUserId == null){
            commentedByUserId = new ArrayList<>();
        }
        commentedByUserId.add(id);
    }


}
