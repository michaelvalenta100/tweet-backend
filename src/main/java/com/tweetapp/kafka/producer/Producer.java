package com.tweetapp.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Component
public final class Producer {

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);

  @Autowired
  private final KafkaTemplate<String, String> kafkaTemplate;

  public Producer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String tweetId) {
    String topicName = "delete-tweet";
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, tweetId);

    //This will check producer result asynchronously to avoid thread blocking
    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onFailure(@NonNull Throwable throwable) {
        logger.error("Failed to send message", throwable);
      }

      @Override
      public void onSuccess(SendResult<String, String> stringStringSendResult) {
        logger.info("Sent message successfully");
      }
    });
    
  }
}
