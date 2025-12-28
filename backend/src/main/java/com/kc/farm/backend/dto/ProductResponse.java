package com.kc.farm.backend.dto;

/** 返却用 */
public record ProductResponse(
		Long id,
		String name,
		int price
){}
