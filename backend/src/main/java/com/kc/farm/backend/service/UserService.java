package com.kc.farm.backend.service;

import com.kc.farm.backend.dto.UserCreateRequest;
import com.kc.farm.backend.dto.UserResponse;

/** Controllerがよぶ窓口 */
public interface UserService {

	/* User登録 */
	UserResponse create(UserCreateRequest request);
	
}
