package com.tweetapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.exception.CustomException;
import com.tweetapp.model.User;
import com.tweetapp.model.UserForgotPassword;
import com.tweetapp.service.UserService;
import com.tweetapp.service.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0")
public class UserController {
	@Autowired
	private UserService userService;
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@GetMapping(value = "/tweets/user/search/{username}")
	public ResponseEntity<?> searchForUsers(@PathVariable String username) {
		logger.info("Searching for user: " + username);
		return new ResponseEntity<>(userService.getUsersByUsername(username), HttpStatus.OK);
	}

	@PostMapping("/tweets/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws CustomException {
		logger.info("Registering new user");
		if (user.getFirstName().isBlank() || user.getlastName().isBlank() || user.getEmail().isBlank()
				|| user.getUsername().isBlank() || user.getPassword().isBlank() || user.getContactNumber().isBlank()) {
			Map<String, String> error = new HashMap<>();
			error.put("errorMsg", "Registration failed. Required details not entered");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
		} else {
			try {
				return ResponseEntity.ok().body(userService.saveUser(user));
			} catch (CustomException e) {
				Map<String, String> response = new HashMap<>();
				response.put("Description", e.getMessage());
				return ResponseEntity.ok().body(response);
			}
		}
	}

	@PostMapping("/tweets/forgot")
	public ResponseEntity<?> forgotPassword(@RequestBody UserForgotPassword user) {
		logger.info("Updating password for " + user.getUsername());
		return ResponseEntity.ok().body(userService.updatePassword(user));

	}

	@GetMapping("/tweets/users/all")
	public ResponseEntity<List<User>> getUsers() {
		logger.info("Getting all users");
		return ResponseEntity.ok().body(userService.getUsers());
	}

	@GetMapping(value = "/tweets/user/{username}")
	public ResponseEntity<?> searchForUser(@PathVariable String username) {
		logger.info("Searching for user: " + username);
		return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
	}



}
