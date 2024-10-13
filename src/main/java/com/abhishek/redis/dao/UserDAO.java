package com.abhishek.redis.dao;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.abhishek.redis.model.User;

@Repository
public class UserDAO {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static final String KEY = "USERS";

	// Creating the user.
	public User saveUser(User user) {
		user.setId(UUID.randomUUID().toString().substring(1, 5));
		redisTemplate.opsForHash().put(KEY, user.getId(), user);
		return user;
	}

	// Get All Users, here we are saving the key against the hashes
	public Map<Object, Object> getAllUser() {

		return redisTemplate.opsForHash().entries(KEY);
	}

	// Getting the user.
	public User getUser(String userId) {
		return (User) redisTemplate.opsForHash().get(KEY, userId);
	}

	// Deleting the user
	public void deleteUser(String userId) {
		redisTemplate.opsForHash().delete(KEY, userId);
	}

}
