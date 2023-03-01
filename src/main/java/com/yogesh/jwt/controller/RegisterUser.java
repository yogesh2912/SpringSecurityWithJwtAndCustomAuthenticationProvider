package com.yogesh.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yogesh.jwt.model.User;
import com.yogesh.jwt.repository.UserRepository;

@RestController
public class RegisterUser {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		
		//System.out.println(user);
		ResponseEntity<String> response = null;
		
		try {
			String hashPwd = passwordEncoder.encode(user.getPwd());
			user.setPwd(hashPwd);
			
			User savedUser = userRepository.save(user);
			
			if(savedUser.getId()>0) {
				response = ResponseEntity.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered");
			}	
		}
		catch(Exception ex) {
			response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
		}
		return response;
	}
}
