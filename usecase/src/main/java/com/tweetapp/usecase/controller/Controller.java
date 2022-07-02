package com.tweetapp.usecase.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.usecase.model.Tweet;
import com.tweetapp.usecase.model.User;
import com.tweetapp.usecase.request.ChangePasswordRequest;
import com.tweetapp.usecase.request.LoginRequest;
import com.tweetapp.usecase.request.RegistrationRequest;
import com.tweetapp.usecase.response.LoginResponse;
import com.tweetapp.usecase.response.RegistrationResponse;

@RestController("/api/v1.0/tweets")
public class Controller {
	
	@PostMapping("/register")
	public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest registrationRequest) {
		
		return ResponseEntity.ok(new RegistrationResponse());
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		
		return ResponseEntity.ok(new LoginResponse());
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/{username}/forgot")
	public ResponseEntity<Boolean> resetPassword(@RequestAttribute String username) {
		
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Tweet>> getTweets() {

		return (ResponseEntity<List<Tweet>>) ResponseEntity.ok();
	}
	
	@GetMapping("/users/all")
	public ResponseEntity<List<User>> getUsers() {

		return (ResponseEntity<List<User>>) ResponseEntity.ok();
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<List<Tweet>> getUserTweets(@RequestAttribute String username) {

		return (ResponseEntity<List<Tweet>>) ResponseEntity.ok();
	}
	
	@PostMapping("/{username}/add")
	public ResponseEntity<Boolean> postTweet(@RequestAttribute String username, @RequestBody Tweet tweet) {

		return ResponseEntity.ok(true);
	}
}
