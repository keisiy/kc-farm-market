package com.kc.farm.backend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET = "my-super-secret-key-my-super-secret-key";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
	
	/*
	 * メソッド名：generateToken
	 * param：email
	 * return：生成されたトークン
	 * 概要：トークン生成
	 * */
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1時間
				.signWith(SECRET_KEY)
				.compact();
	}
	
	/*
	 * メソッド名：extractEmail
	 * param：token
	 * return：取得されたemail
	 * 概要：トークンからemail取得
	 * */
	public String extractEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	/*
	 * メソッド名：validateToken
	 * param：token
	 * return：boolean
	 * 概要：トークン検証
	 * */
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(SECRET_KEY)
					.build()
					.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}
