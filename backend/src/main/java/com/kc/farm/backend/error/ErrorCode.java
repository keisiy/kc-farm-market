package com.kc.farm.backend.error;

import org.springframework.http.HttpStatus;

/** エラーコードを管理するEnum */
public enum ErrorCode {
	
	/* === Product === */
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "商品が見つかりません"),
    PRODUCT_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "P-002", "商品入力値が不正です"),
	
	/* === Common === */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-001", "サーバーエラーが発生しました");

    /* === フィールド === */ 
    private final HttpStatus status;
    private final String code;
    private final String message;
    
    private ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
    
    public HttpStatus status() {
        return status;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
    
}
