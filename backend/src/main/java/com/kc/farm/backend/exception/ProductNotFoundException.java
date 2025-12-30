package com.kc.farm.backend.exception;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(Long id) {
		super("product not found. id=" + id);
	}
	
}
