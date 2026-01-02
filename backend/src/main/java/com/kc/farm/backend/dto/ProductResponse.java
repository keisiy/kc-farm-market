package com.kc.farm.backend.dto;

import com.kc.farm.backend.entity.Product;

/** 返却用DTO */
public record ProductResponse(
		Long id,
		String name,
		int price
){
	/** Entity→DTO変換 */
	public static ProductResponse from(Product product) {
		return new ProductResponse(product.getId(), product.getName(), product.getPrice());
	}
}
