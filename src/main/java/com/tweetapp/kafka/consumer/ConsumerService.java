package com.tweetapp.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tweetapp.repository.TweetRepository;

@Service
public final class ConsumerService {

	@Autowired
	private TweetRepository tweetRepo;

	Logger logger = LoggerFactory.getLogger(ConsumerService.class);

	public ConsumerService(TweetRepository tweetRepo) {
		this.tweetRepo = tweetRepo;
	}

	@KafkaListener(topics = "delete-tweet", containerFactory = "kafkaListenerContainerFactory")
	public void consume(String tweetId) {
		logger.info("Deleting tweet: " + tweetRepo.findById(tweetId));

		tweetRepo.deleteById(tweetId.toString());

	}
}
