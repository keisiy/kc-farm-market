package com.kc.farm.backend.dto;

public record ProductUpdateRequest(
		String name,
		int price
) {}
