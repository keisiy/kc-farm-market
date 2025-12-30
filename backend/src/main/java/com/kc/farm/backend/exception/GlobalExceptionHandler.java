package com.kc.farm.backend.exception;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kc.farm.backend.dto.ErrorResponse;

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
	
}
