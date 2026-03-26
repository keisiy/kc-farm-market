package com.kc.farm.backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.farm.backend.dto.AuthRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager; 
	
	public AuthController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request) {
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				);
		
		authenticationManager.authenticate(authToken);
		
		return "ログイン成功";
	}
	
}
