package com.kc.farm.backend.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kc.farm.backend.dto.ErrorResponse;
import com.kc.farm.backend.dto.ValidationErrorResponse;

/** 例外情報作成を一手に担う */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFound(
			ProductNotFoundException ex,
			HttpServletRequest request
		) {
		/* エラー用DTOを生成 */
		ErrorResponse error = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				"PRODUCT_NOT_FOUND",
				ex.getMessage(),
				request.getRequestURI(),
				LocalDateTime.now()
		);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidationError(
			MethodArgumentNotValidException ex,
			HttpServletRequest request
		){
		Map<String, String> details = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(
						FieldError::getField,
						FieldError::getDefaultMessage,
						(a, b) -> a
				));
		
		ValidationErrorResponse error = new ValidationErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				"VALIDATION_ERROR",
				"入力値が不正です。",
				details,
				request.getRequestURI(),
				LocalDateTime.now()
			);
		
		return ResponseEntity.badRequest().body(error);
	}
	
	
	
}
