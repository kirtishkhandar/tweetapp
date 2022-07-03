package com.tweetapp.usecase.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.usecase.model.Tweet;
import com.tweetapp.usecase.model.User;
import com.tweetapp.usecase.repository.UserRepository;
import com.tweetapp.usecase.request.ChangePasswordRequest;
import com.tweetapp.usecase.request.LoginRequest;
import com.tweetapp.usecase.request.RegistrationRequest;
import com.tweetapp.usecase.response.LoginResponse;
import com.tweetapp.usecase.response.RegistrationResponse;
import com.tweetapp.usecase.security.jwt.JwtUtils;
import com.tweetapp.usecase.security.service.UserDetailsImpl;

@RestController("/api/v1.0/tweets")
public class Controller {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/register")
	public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest registrationRequest) {

		if (Boolean.TRUE.equals(userRepository.existsByUsername(registrationRequest.getEmail()))) {
			return ResponseEntity.badRequest().body(new RegistrationResponse("Error: Username is already taken!"));
		}

		return ResponseEntity.ok(new RegistrationResponse("Registration Successful."));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUsername(), roles));
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
