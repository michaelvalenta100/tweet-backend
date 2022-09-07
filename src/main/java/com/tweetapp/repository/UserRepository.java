package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweetapp.model.User;

public interface UserRepository extends MongoRepository<User, Long>{
	User findByUsername(String username);
	
	@Query("{'username':{'$regex':'?0','$options':'i'}}")
    List<User> searchByUsername(String username);
	
	
}
