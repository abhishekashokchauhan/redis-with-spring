package com.abhishek.redis.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.redis.dao.UserDAO;
import com.abhishek.redis.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@PostMapping
	public User createUser(@RequestBody User user) {

		return userDAO.saveUser(user);
	}

	@GetMapping
	public List<User> getAllUser() {
		Map<Object, Object> users = userDAO.getAllUser();
		Collection<Object> values = users.values();
		List<User> collect = values.stream().map(value -> (User) value).collect(Collectors.toList());
		return collect;
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable String userId) {
		return userDAO.getUser(userId);
	}
}
