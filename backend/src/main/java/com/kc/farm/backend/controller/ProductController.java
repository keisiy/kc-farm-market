package com.kc.farm.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.farm.backend.record.ProductResponse;
import com.kc.farm.backend.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
        this.productService = productService;
    }

	@GetMapping
    public List<ProductResponse> getProducts() {
        return productService.findAll();
    }
	
}
