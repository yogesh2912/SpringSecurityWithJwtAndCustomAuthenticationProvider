package com.yogesh.jwt.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yogesh.jwt.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	List<User> findByEmail(String email);
}
