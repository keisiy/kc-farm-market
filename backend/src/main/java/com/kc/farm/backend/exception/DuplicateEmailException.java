package com.kc.farm.backend.exception;

import com.kc.farm.backend.error.ErrorCode;

/** リクエストのユーザーのmailがすでに登録されていることを表す例外クラス */
public class DuplicateEmailException extends RuntimeException {
	
	private final String field;
	
	public DuplicateEmailException(String email) {
		super("This email address is already registered. mail=" + email);
		this.field = "email";
	}
	
	public ErrorCode getErrorCode() {
		return ErrorCode.MAIL_DUPLICATION_ERROR;
	}
	
	public String getField() {
		return field;
	}
	
}
