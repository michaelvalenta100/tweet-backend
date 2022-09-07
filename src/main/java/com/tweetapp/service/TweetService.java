package com.tweetapp.service;

import java.util.List;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.TweetLike;
import com.tweetapp.model.TweetUpdate;

public interface TweetService {
	List<Tweet> getAllTweets();
	List<Tweet> getUserTweets(String email);
	Tweet save(Tweet tweet);
	Tweet updateTweet(TweetUpdate tweetUpdate);
	void deleteTweet(Tweet tweet);
	Tweet likeTweet(TweetLike tweetLike);
	Tweet replyTweet(Tweet tweet, String tweetId);
	
	
	Tweet showReply(Tweet tweet);
}
