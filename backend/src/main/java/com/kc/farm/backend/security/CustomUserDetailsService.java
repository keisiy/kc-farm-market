package com.kc.farm.backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kc.farm.backend.entity.User;
import com.kc.farm.backend.repository.UserRepository;

/*
 * 役割：ログイン時にユーザーをDBから取得する。
 * */

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository
			.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
		return org.springframework.security.core.userdetails.User
				.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}
	
}
