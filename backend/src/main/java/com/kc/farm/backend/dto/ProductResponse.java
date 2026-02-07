package com.kc.farm.backend.dto;

import com.kc.farm.backend.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 返却用DTO */
@Getter
@AllArgsConstructor
public class ProductResponse {
	private Long id;
	private String name;
	private int price;
	
	/** Entity→DTO変換 */
	public static ProductResponse from(Product product) {
		return new ProductResponse(product.getId(), product.getName(), product.getPrice());
	}
}
