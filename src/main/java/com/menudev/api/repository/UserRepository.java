package com.menudev.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.menudev.api.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{

	Optional<UserEntity> findByEmail(@Param("email")String email);
}
