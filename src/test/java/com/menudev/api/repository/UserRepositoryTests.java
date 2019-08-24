 package com.menudev.api.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.menudev.api.entity.UserEntity;

/**
 * This class handle User integration tests
 * 
 * @author menuka
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	UserRepository userRepositoy;
	/**
	 * insert records for testing
	 */
	@Before
	public void before() {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("kals@gmail.com");
		userEntity.setName("kas");
		userRepositoy.save(userEntity);
		
	}
	/**
	 * Test findByEmail behavior
	 */
	@Test
	public void testUserFindByEmail_happyPath() {
		Optional<UserEntity> user = userRepositoy.findByEmail("kals@gmail.com");
		assertEquals("kas", user.get().getName());
	}
	
	/**
	 * clean the data to leave each tests independent
	 */
	@After
	public void after() {
		userRepositoy.deleteAll();
		assertEquals(Optional.empty(), userRepositoy.findByEmail("kals@gmail.com"));
	}
}
