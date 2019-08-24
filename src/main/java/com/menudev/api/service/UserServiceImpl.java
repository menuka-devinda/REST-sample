package com.menudev.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menudev.api.domain.User;
import com.menudev.api.entity.UserEntity;
import com.menudev.api.repository.UserRepository;

@Service
@Transactional
class UserServiceImpl implements UserService {
	private static final Logger LOG =Logger.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		LOG.info("get all users from db");
		return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(userEntity -> {

			return convertEntityToDto(userEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<User> getUserById(Integer id) {

		if (userRepository.findById(id).isPresent()) {
			UserEntity userEntity = userRepository.findById(id).get();
			return Optional.of(convertEntityToDto(userEntity));
		} else {
			LOG.info("user with id "+id + " not existing");
			return Optional.ofNullable(null);
		}
	}

	@Override
	public User createUser(User user) {

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());

		if (userRepository.findByEmail(user.getEmail()).isEmpty()) {

			UserEntity createUserEntity = userRepository.save(userEntity);
			LOG.info("user with id "+user.getEmail() + " created successfully in db");
			return convertEntityToDto(createUserEntity);
			
		} else {
			LOG.info("user with id "+user.getEmail() + " already existing in db");
			throw new IllegalArgumentException("User already exists");
		}

	}

	@Override
	public User updateUser(Integer id, User user) {

		if (!userRepository.findById(id).isEmpty()) {
			UserEntity userEntity = userRepository.findById(id).get();

			userEntity.setEmail(user.getEmail());
			userEntity.setName(user.getName());

			UserEntity updatedUserEntity = userRepository.save(userEntity);
			LOG.info("user with id "+user.getEmail() + " updated successfully in db");
			return convertEntityToDto(updatedUserEntity);
		} else {
			LOG.info("user with id "+user.getEmail() + "doesn't exist ");
			throw new IllegalArgumentException("Invalid user");
		}
	}

	@Override
	public void deleteUser(Integer id) {

		if (!userRepository.findById(id).isEmpty()) {
			LOG.info("user with id "+id + " deleted from db");
			userRepository.deleteById(id);
		} else {
			LOG.info("user with id "+id + "doesn't exist ");
			throw new IllegalArgumentException("Invalid user id");
		}
	}
	
	/**
	 * @param createUserEntity
	 * @return
	 */
	private User convertEntityToDto(UserEntity createUserEntity) {
		User userCreated = new User();
		userCreated.setEmail(createUserEntity.getEmail());
		userCreated.setId(createUserEntity.getId());
		userCreated.setName(createUserEntity.getName());
		return userCreated;
	}
}
