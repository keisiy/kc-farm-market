/**
 * フロント用の型定義
 */
export type FieldError = {
  field: string;
  message: string;
};

export type ApiErrorResponse = {
  code: string;
  message: string;
  /** errorsは省略可能プロパティ */
  errors?: FieldError[];/** FieldError[] | undefined　となる */
};
