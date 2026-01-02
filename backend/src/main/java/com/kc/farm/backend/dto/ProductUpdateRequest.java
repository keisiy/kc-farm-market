package com.kc.farm.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/** 更新用DTO */
public record ProductUpdateRequest(
		@NotBlank(message = "name is required")
		String name,
		
		@Min(value = 1, message = "price must be >= 1")
		int price
) {}
