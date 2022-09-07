package com.tweetapp.service;

import java.util.List;
import java.util.Map;

import com.tweetapp.exception.CustomException;
import com.tweetapp.model.User;
import com.tweetapp.model.UserForgotPassword;

public interface UserService {
	Map<String, String> saveUser (User user) throws CustomException;
	User getUser(String username);
	List<User> getUsers();
	User updatePassword(UserForgotPassword user);
	List<User> getUsersByUsername(String username);
	

}

