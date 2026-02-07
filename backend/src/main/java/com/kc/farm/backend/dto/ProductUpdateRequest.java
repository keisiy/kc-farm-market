package com.kc.farm.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 更新用DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
	
	@NotBlank(message = "name is required")
	private String name;
	
	@Min(value = 1, message = "price must be >= 1")
	private int price;
}
