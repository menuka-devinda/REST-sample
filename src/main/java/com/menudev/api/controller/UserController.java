package com.menudev.api.controller;

import java.rmi.UnexpectedException;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menudev.api.domain.User;
import com.menudev.api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger LOG = Logger.getLogger(UserController.class);
	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		LOG.info("get all users");
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) throws UnexpectedException {
		LOG.info("get user by user id: " + id);
		if (null == id) {
			throw new IllegalArgumentException("Id is invalid ");
		}
		User user = userService.getUserById(id).orElseThrow();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user) throws UnexpectedException {
		// Preconditions.checkNotNull(resource);
		LOG.info("create new user ");
		User createdUser = userService.createUser(user);
		if (null != createdUser) {
			LOG.info("new user with id " + createdUser.getId() + " is created successfully");
			return new ResponseEntity<String>("User created succefully", HttpStatus.NO_CONTENT);
		} else {
			throw new UnexpectedException("Unexpected Error");
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody User user) {
		// Preconditions.checkNotNull(resource);
		// RestPreconditions.checkNotNull(service.getById(resource.getId()));
		LOG.info("update user with id: " + id);
		userService.updateUser(id, user);
		LOG.info("user with id: " + id + " is updated successfully");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		LOG.info("delete user with id: " + id);
		userService.deleteUser(id);
		LOG.info("user with id: " + id + " is deleted successfully");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
