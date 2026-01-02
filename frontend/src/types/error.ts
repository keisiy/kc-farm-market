/**
 * フロント用の型定義
 */
export type FieldError = {
  field: string;
  message: string;
};

export type ErrorResponse = {
  code: string;
  message: string;
  errors?: FieldError[];
};
