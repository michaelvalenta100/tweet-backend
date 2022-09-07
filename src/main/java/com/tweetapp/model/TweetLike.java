package com.tweetapp.model;

public class TweetLike {
	
	private String tweetId;
	private String userId;
	
	public TweetLike(){
		
	}
	
	

	public TweetLike(String tweetId, String userId) {
		this.tweetId = tweetId;
		this.userId = userId;
	}



	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	

}
