package com.tweetapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Tweet {
	@Id
	private String id;
	private String username;
	private String tweetMsg;
	private String time;
	private List<String> like;
	private String tagText;
	private List<Tweet> replyTweet;
	private Boolean showReplys;
	private String tweetReply;
	
	
	
	

	public Tweet(String id, String username, String tweetMsg, String time, List<String> like, String tagText,
			List<Tweet> replyTweet, Boolean showReplys, String tweetReply) {
		this.id = id;
		this.username = username;
		this.tweetMsg = tweetMsg;
		this.time = time;
		this.like = like;
		this.tagText = tagText;
		this.replyTweet = replyTweet;
		this.showReplys = showReplys;
		this.tweetReply = tweetReply;
	}
	
	

	public String getTweetReply() {
		return tweetReply;
	}



	public void setTweetReply(String tweetReply) {
		this.tweetReply = tweetReply;
	}



	public Boolean getShowReplys() {
		return showReplys;
	}

	public void setShowReplys(Boolean showReplys) {
		this.showReplys = showReplys;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTweetMsg() {
		return tweetMsg;
	}

	public void setTweetMsg(String tweetMsg) {
		this.tweetMsg = tweetMsg;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<String> getLike() {
		return like;
	}

	public void setLike(List<String> like) {
		this.like = like;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}

	public List<Tweet> getReplyTweet() {
		return replyTweet;
	}

	public void setReplyTweet(List<Tweet> replyTweet) {
		this.replyTweet = replyTweet;
	}



	@Override
	public String toString() {
		return "Tweet [id=" + id + ", username=" + username + ", tweetMsg=" + tweetMsg + ", time=" + time + ", like="
				+ like + ", tagText=" + tagText + ", replyTweet=" + replyTweet + ", showReplys=" + showReplys
				+ ", tweetReply=" + tweetReply + "]";
	}


	
	

	
}
