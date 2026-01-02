package com.kc.farm.backend.error;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kc.farm.backend.exception.ProductNotFoundException;

/** 例外情報作成を担うクラス */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/* =========================
     * 商品が存在しない
     * ========================= */
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleProductNotFound(
			ProductNotFoundException ex
		) {
		/* エラー用DTOを生成 */
		return new ErrorResponse(
				ex.getMessage(),
				List.of()
		);
	}
	
	/* =========================
     * Validationエラー
     * ========================= */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleValidationError(
			MethodArgumentNotValidException ex
		){
		BindingResult result = ex.getBindingResult();
		
		List<FieldError> errors = result.getFieldErrors().stream()
				.map(e -> new FieldError(
						e.getField(),
						e.getDefaultMessage())
					)
				.toList();
		
		return new ErrorResponse("validation error", errors);
	}
}
