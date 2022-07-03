package com.tweetapp.usecase.repository;

import java.util.Optional;

import com.tweetapp.usecase.model.User;

public interface UserRepository {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

}
