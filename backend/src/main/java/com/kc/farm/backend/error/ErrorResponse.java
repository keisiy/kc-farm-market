package com.kc.farm.backend.error;

import java.time.LocalDateTime;
import java.util.List;

/** エラー情報を受け取りフロントへ返却するクラス */
public record ErrorResponse(
			/* コード */
			String code,
			/* 共通メッセージ */
			String message,
			/* 詳細 フィールドごとに保持 */
			List<FieldError> errors,
			/* 時刻 */
			LocalDateTime timestamp
) {
	/* ErrorResponseを返すファクトリメソッド */
	public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.code(),
                errorCode.message(),
                null,
                LocalDateTime.now()
        );
    }
	public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
        return new ErrorResponse(
                errorCode.code(),
                errorCode.message(),
                errors,
                LocalDateTime.now()
        );
    }
}
