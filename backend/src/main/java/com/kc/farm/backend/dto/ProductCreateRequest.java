package com.kc.farm.backend.dto;

public record ProductCreateRequest(
		
		/* idは不要。DBが採番 */
		
		String name,
		int price
) {}
