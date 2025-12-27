package com.kc.farm.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.farm.backend.entity.ProductEntity;
import com.kc.farm.backend.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	@GetMapping
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }
	
}
