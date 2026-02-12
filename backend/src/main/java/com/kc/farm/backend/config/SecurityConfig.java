package com.kc.farm.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					/* 以下のパターンのURLは誰でもアクセス可能とする */
					.requestMatchers("/api/**").permitAll()
					.anyRequest().authenticated()
			)
			/* フォームログイン認証（ログイン画面）を無効化 */
			.formLogin(form -> form.disable())
			/* HTTP基本認証を無効化 */
			.httpBasic(basic -> basic.disable());
		
		return http.build();
	}

}
