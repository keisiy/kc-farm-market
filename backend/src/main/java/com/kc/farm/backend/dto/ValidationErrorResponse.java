package com.kc.farm.backend.dto;

import java.time.LocalDateTime;
import java.util.Map;

/** バリデーション用DTO */
public record ValidationErrorResponse(
		int status,
		String error,
		String message,
		Map<String, String> details,
		String path,
		LocalDateTime timestamp
) {}
