package com.tweetapp.model;

public class TweetReply {

	private Tweet originalTweet;
	private Tweet tweetReply;

	public Tweet getOriginalTweet() {
		return originalTweet;
	}

	public void setOriginalTweet(Tweet originalTweet) {
		this.originalTweet = originalTweet;
	}

	public Tweet getTweetReply() {
		return tweetReply;
	}

	public void setTweetReply(Tweet tweetReply) {
		this.tweetReply = tweetReply;
	}

}
