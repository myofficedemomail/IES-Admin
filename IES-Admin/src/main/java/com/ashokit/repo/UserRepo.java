package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUserEmail(String userEmail);
}
