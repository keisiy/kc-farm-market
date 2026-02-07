package com.kc.farm.backend.exception;

import com.kc.farm.backend.error.ErrorCode;

/** リクエストのユーザーのmailがすでに登録されていることを表す例外クラス */
public class DuplicateEmailException extends RuntimeException {
	
	public DuplicateEmailException(String mail) {
		super("This email address is already registered. mail=" + mail);
	}
	
	public ErrorCode getErrorCode() {
		return ErrorCode.MAIL_DUPLICATION_ERROR;
	}
	
}
