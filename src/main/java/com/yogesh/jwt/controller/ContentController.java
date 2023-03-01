package com.yogesh.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

	@GetMapping("/content")
	public String contentController( Authentication auth) {
		return auth.getName();
	}
}
