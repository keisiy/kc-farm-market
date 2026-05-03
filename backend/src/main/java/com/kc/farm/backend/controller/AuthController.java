package com.kc.farm.backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.farm.backend.dto.AuthRequest;
import com.kc.farm.backend.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager; 
	private final JwtUtil jwtUtil;
	
	public AuthController(AuthenticationManager authenticationManager,
			JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request) {
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				);
		
		authenticationManager.authenticate(authToken);
		
		// JWT発行
		String token = jwtUtil.generateToken(request.getEmail());
		
		// JWT返却
		return token;
	}
	
}
