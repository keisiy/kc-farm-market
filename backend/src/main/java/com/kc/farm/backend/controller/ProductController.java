package com.kc.farm.backend.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.farm.backend.dto.ProductCreateRequest;
import com.kc.farm.backend.dto.ProductResponse;
import com.kc.farm.backend.dto.ProductUpdateRequest;
import com.kc.farm.backend.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	private final ProductService productService;
	
	/* ここでProductServiceImplが注入される */
	public ProductController(ProductService productService) {
        this.productService = productService;
    }

	@GetMapping
    public List<ProductResponse> getProducts() {
		return productService.findAll();
    }
	
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable Long id) {
		/* リクエストされたProductを取得 */
		return productService.findById(id);
	}
	
	/* 商品登録 */
	@PostMapping
	public ProductResponse createProduct(@RequestBody @Valid ProductCreateRequest request) {
		return productService.create(request);
	}
	
	@PutMapping("/{id}")
	public ProductResponse updateProduct(
			@PathVariable Long id,
			@RequestBody @Valid ProductUpdateRequest updated
			) {
		return productService.update(id, updated);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productService.delete(id);
	}
	
}
