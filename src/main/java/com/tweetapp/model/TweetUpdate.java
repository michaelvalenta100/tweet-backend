package com.tweetapp.model;

public class TweetUpdate {
	
	private String tweetId;
	private String tweetMsg;
	
	public TweetUpdate() {
		
	}

	public TweetUpdate(String tweetId, String tweetMsg) {
		this.tweetId = tweetId;
		this.tweetMsg = tweetMsg;
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getTweetMsg() {
		return tweetMsg;
	}

	public void setTweetMsg(String tweetMsg) {
		this.tweetMsg = tweetMsg;
	}
	
	

}
