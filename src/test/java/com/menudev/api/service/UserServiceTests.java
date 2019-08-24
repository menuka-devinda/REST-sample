package com.menudev.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.menudev.api.domain.User;
import com.menudev.api.entity.UserEntity;
import com.menudev.api.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTests {

	@Mock
	UserRepository userRepository;

	UserService userService;

	@Before
	public void before() {
		userService = new UserServiceImpl(userRepository);
	}

	@Test
	public void testGetUserById_happyPath() {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("gona@gmail.com");
		userEntity.setId(2);
		userEntity.setName("Gona");
		Optional<UserEntity> value = Optional.of(userEntity);
		when(userRepository.findById(2)).thenReturn(value);
		Optional<User> user = userService.getUserById(2);
		assertEquals("gona@gmail.com", user.get().getEmail());
	}

	@Test
	public void testGetUserById_invalidId() {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("gona@gmail.com");
		userEntity.setId(2);
		userEntity.setName("Gona");
		Optional<UserEntity> value = Optional.of(userEntity);
		when(userRepository.findById(2)).thenReturn(value);
		Optional<User> user = userService.getUserById(3);
		assertEquals(Optional.empty(), user);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetUserById_NULL_value() {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("gona@gmail.com");
		userEntity.setId(2);
		userEntity.setName("Gona");
		when(userRepository.findById(null)).thenAnswer( invocation -> { throw new IllegalArgumentException("Unexpected Error"); });
		userService.getUserById(null);
	}

}
