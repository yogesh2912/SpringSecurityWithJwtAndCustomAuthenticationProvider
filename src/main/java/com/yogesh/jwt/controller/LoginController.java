package com.yogesh.jwt.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping("/loginUser")
	public String loginUserWithJwtToken(Authentication auth) {
		return auth.getName();
	}
}