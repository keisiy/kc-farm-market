package com.kc.farm.backend.exception;

import com.kc.farm.backend.error.ErrorCode;

/** リクエストの商品が存在しないことを表す例外クラス */
public class ProductNotFoundException extends RuntimeException {
	
	private final ErrorCode errorCode;

	public ProductNotFoundException(Long id) {
		super("product not found. id=" + id);
		this.errorCode = ErrorCode.PRODUCT_NOT_FOUND;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
}
