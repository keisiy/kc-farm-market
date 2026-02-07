package com.kc.farm.backend.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.farm.backend.dto.UserCreateRequest;
import com.kc.farm.backend.dto.UserResponse;
import com.kc.farm.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;
	
	/* ここでUserServiceImplが注入される */
	public UserController(UserService userService) {
        this.userService = userService;
    }
	
	/* ユーザー登録 */
	@PostMapping
	public UserResponse createUser(@RequestBody @Valid UserCreateRequest request) {
		return userService.create(request);
	}
	
}
