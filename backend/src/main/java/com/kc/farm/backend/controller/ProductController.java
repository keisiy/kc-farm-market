package com.kc.farm.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.farm.backend.entity.Product;
import com.kc.farm.backend.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductRepository productRepository;
	
	public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	@GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
	
	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	/* 商品登録 */
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/{id}")
	public Product update(
			@PathVariable Long id,
			@RequestBody Product updated
			) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product 	not found"));
		
		product.setName(updated.getName());
		product.setPrice(updated.getPrice());
		
		return productRepository.save(product);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productRepository.deleteById(id);
	}
	
}
