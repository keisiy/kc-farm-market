package com.kc.farm.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 登録用DTO */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest{
	/* idは不要。DBが採番 */
	
	@NotBlank(message = "name is required")
	private String name;
	
	@Min(value = 1, message = "price must be >= 1")
	private int price;
}
