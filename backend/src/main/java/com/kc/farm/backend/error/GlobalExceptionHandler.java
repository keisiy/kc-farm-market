package com.kc.farm.backend.error;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kc.farm.backend.exception.ProductNotFoundException;

/** 例外情報作成を担うクラス */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/* =========================
     * 商品が存在しない
     * ========================= */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFound(
			ProductNotFoundException ex
		) {
		/* エラーコードを取得 */
		ErrorCode errorCode = ex.getErrorCode();
		
		/* エラー用DTOを生成 */
		return ResponseEntity.status(errorCode.status())
				.body(ErrorResponse.of(errorCode));
	}
	
	/* =========================
     * Validationエラー
     * ========================= */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationError(
			MethodArgumentNotValidException ex
		){
		/* エラーコードを取得 */
		ErrorCode errorCode = ErrorCode.PRODUCT_VALIDATION_ERROR;
		
		BindingResult result = ex.getBindingResult();
		
		List<FieldError> errors = result.getFieldErrors().stream()
				.map(e -> new FieldError(
						e.getField(),
						e.getDefaultMessage())
					)
				.toList();
		
		return ResponseEntity.status(errorCode.status())
				.body(ErrorResponse.of(errorCode, errors));
	}
}
