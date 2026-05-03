package com.kc.farm.backend.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kc.farm.backend.security.CustomUserDetailsService;
import com.kc.farm.backend.security.JwtAuthFilter;
import com.kc.farm.backend.security.JwtUtil;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * このアプリのHTTPリクエストを、どんな順番・どんなルールで認証/認可するか
	 * を組み立てる
	 * */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http,
			JwtAuthFilter jwtAuthFilter) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			// Cors設定
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.authorizeHttpRequests(auth -> auth
					/* 以下のパターンのURLは誰でもアクセス可能とする */
					.requestMatchers("/api/auth/**").permitAll()
					.anyRequest().authenticated()
			)
			.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	/*
	 * Cors設定インスタンスを生成する
	 * */
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil,
										CustomUserDetailsService userDetailsService) {
		return new JwtAuthFilter(jwtUtil, userDetailsService);
	}

}
