package com.kc.farm.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/** 登録用DTO */
public record ProductCreateRequest(
		
		/* idは不要。DBが採番 */
		
		@NotBlank(message = "name is required")
		String name,
		
		@Min(value = 1, message = "price must be >= 1")
		int price
) {}
