package com.tweetapp.service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tweetapp.exception.CustomException;
import com.tweetapp.model.User;
import com.tweetapp.model.UserForgotPassword;
import com.tweetapp.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public List<User> getUsersByUsername(String username) {
		return userRepo.searchByUsername(username);
	}

	@Override
	public Map<String, String> saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Map<String, String> response = new HashMap<>();
		try {
			userRepo.save(user);
		} catch (DuplicateKeyException exception) {
			throw new CustomException("Email or Username already in use, please enter new details.");

		}
		response.put("registration_status", "Successful");
		return response;
	}

	@Override
	public User getUser(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public User updatePassword(UserForgotPassword user) {
		User user1 = userRepo.findByUsername(user.getUsername());
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user1);
		return user1;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			logger.info("User not found in Database");
			throw new UsernameNotFoundException("User not found in Database");
		} else {

		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}


}
