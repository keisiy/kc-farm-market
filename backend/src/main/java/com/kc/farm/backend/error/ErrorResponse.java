package com.kc.farm.backend.error;

import java.util.List;

public record ErrorResponse(
			String name,
			List<FieldError> errors
) {}
