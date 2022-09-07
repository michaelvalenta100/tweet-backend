package com.tweetapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.kafka.producer.Producer;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.TweetLike;
import com.tweetapp.model.TweetReply;
import com.tweetapp.model.TweetUpdate;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.TweetServiceImpl;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0")
public class TweetController {

	@Autowired
	private TweetService tweetService;
	
	Logger logger = LoggerFactory.getLogger(TweetServiceImpl.class);

	@Autowired
	private Producer producer;

	@DeleteMapping("/tweets/delete")
	public void deleteTweet(@RequestHeader String tweetId) {
		producer.sendMessage(tweetId);
	}

	@PostMapping("/tweets/add")
	public ResponseEntity<?> postTweet(@RequestBody Tweet tweet) {
		logger.info("Posting tweet: "+tweet);
		return ResponseEntity.ok().body(tweetService.save(tweet));
	}

	@GetMapping("/tweets/all")
	public ResponseEntity<List<Tweet>> getAllTweets() {
		logger.info("Getting all tweets");
		return ResponseEntity.ok().body(tweetService.getAllTweets());
	}

	@GetMapping("/tweets/{username}")
	public ResponseEntity<List<Tweet>> getUserTweets(@PathVariable String username) {
		logger.info("Getting user tweets for "+username);
		return ResponseEntity.ok().body(tweetService.getUserTweets(username));
	}

	@PutMapping("/tweets/update")
	public ResponseEntity<?> updateTweet(@RequestBody TweetUpdate tweetUpdate) {
		logger.info("Updating tweet");
		return ResponseEntity.ok().body(tweetService.updateTweet(tweetUpdate));

	}

	@PutMapping("/tweets/like")
	public ResponseEntity<Tweet> likeTweet(@RequestBody TweetLike tweetLike) {
		logger.info("Liking / unliking tweet");
		return ResponseEntity.ok().body(tweetService.likeTweet(tweetLike));
	}
	
	@PutMapping("/tweets/showReply")
	public ResponseEntity<Tweet> showReply(@RequestBody Tweet tweet) {
		logger.info("Showing / Hiding tweet replys");
		return ResponseEntity.ok().body(tweetService.showReply(tweet));
	}

	@PostMapping("/tweets/reply")
	public ResponseEntity<Tweet> replyTweet(@RequestBody TweetReply tweet) {
		logger.info("Replying to tweet: "+tweet.getOriginalTweet());
		String parentTweetId = tweet.getOriginalTweet().getId();
		Tweet tempTweet = tweet.getTweetReply();
		return ResponseEntity.ok().body(tweetService.replyTweet(tempTweet, parentTweetId));
	}

}
