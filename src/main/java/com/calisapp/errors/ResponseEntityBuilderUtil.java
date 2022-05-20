package com.calisapp.errors;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilderUtil {
	public static ResponseEntity<Object> build(ApiError apiError) {
	      return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}