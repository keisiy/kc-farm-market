package com.kc.farm.backend.error;

public record FieldError(
		String field,
		String message
) {}
