package com.kc.farm.backend.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** バリデーション用DTO */
@Getter
@AllArgsConstructor
public class ValidationErrorResponse {
	private int status;
	private String error;
	private String message;
	private Map<String, String> details;
	private String path;
	private LocalDateTime timestamp;
}
