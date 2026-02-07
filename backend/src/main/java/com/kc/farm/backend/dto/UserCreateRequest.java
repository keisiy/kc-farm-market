package com.kc.farm.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 登録用DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
	
	/* idは不要。DBが採番 */
	
	@NotBlank(message = "email is required") // ブランクNG
	@Email(message = "email format is invalid") // email形式チェック
	private String email;
	
	@NotBlank(message = "password is required")
	private String password;
}
