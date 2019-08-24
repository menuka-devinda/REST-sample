package com.menudev.api.service;

import java.util.List;
import java.util.Optional;

import com.menudev.api.domain.User;

public interface UserService {

	List<User> getAllUsers();

	Optional<User> getUserById(Integer id);

	User createUser(User user);

	User updateUser(Integer id, User user);

	void deleteUser(Integer id);
}
