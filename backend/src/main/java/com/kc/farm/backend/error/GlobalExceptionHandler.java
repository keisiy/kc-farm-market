package com.kc.farm.backend.error;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kc.farm.backend.exception.DuplicateEmailException;
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
     * メール重複エラー
     * ========================= */
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateEmail(
			DuplicateEmailException ex
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
		ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
		
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
