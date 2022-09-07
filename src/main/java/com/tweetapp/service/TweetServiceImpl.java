package com.tweetapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//changes
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.TweetLike;
import com.tweetapp.model.TweetUpdate;
import com.tweetapp.repository.TweetRepository;

@Service
@Transactional
public class TweetServiceImpl implements TweetService {

	@Autowired
	private TweetRepository tweetRepo;

	public Tweet likeTweet(TweetLike tweetLike) {

		Tweet temp = tweetRepo.findById(String.valueOf(tweetLike.getTweetId()));
		List<String> likeList = temp.getLike();
		if (likeList == null) {
			likeList = new ArrayList<>();
		}
		if (!likeList.contains(tweetLike.getUserId())) {
			likeList.add(tweetLike.getUserId());
		} else {
			likeList.remove(tweetLike.getUserId());
		}
		temp.setLike(likeList);
		tweetRepo.save(temp);
		return temp;

	}

	public Tweet showReply(Tweet tweet) {

		Tweet temp = tweetRepo.findById(String.valueOf(tweet.getId()));
		Boolean showReply = temp.getShowReplys();
		if (showReply == true) {
			temp.setShowReplys(false);
		} else {
			temp.setShowReplys(true);
		}
		tweetRepo.save(temp);
		return temp;
	}

	@Override
	public List<Tweet> getAllTweets() {
		return tweetRepo.findAll();
	}

	@Override
	public List<Tweet> getUserTweets(String username) {
		return tweetRepo.findByUsername(username);
	}

	@Override
	public Tweet save(Tweet tweet) {
		tweetRepo.save(tweet);
		return tweet;
	}

	@Override
	public Tweet updateTweet(TweetUpdate tweetUpdate) {
		Tweet temp = tweetRepo.findById(String.valueOf(tweetUpdate.getTweetId()));
		temp.setTweetMsg(tweetUpdate.getTweetMsg());
//		temp.setTime(tweet.getTime());
		tweetRepo.save(temp);
		return temp;
	}

	@Override
	public void deleteTweet(Tweet tweet) {
		tweetRepo.deleteById(tweet.getId());
	}

	@Override
	public Tweet replyTweet(Tweet tweet, String tweetId) {
		Tweet temp = tweetRepo.findById(tweetId);
		List<Tweet> replyTweets = temp.getReplyTweet();
		tweet.setId(String.valueOf(replyTweets.size() + 1));
		replyTweets.add(tweet);
		temp.setReplyTweet(replyTweets);
		tweetRepo.save(temp);
		return temp;

	}

}