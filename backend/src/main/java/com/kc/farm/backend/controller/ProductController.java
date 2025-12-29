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

import com.kc.farm.backend.dto.ProductCreateRequest;
import com.kc.farm.backend.dto.ProductResponse;
import com.kc.farm.backend.dto.ProductUpdateRequest;
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
    public List<ProductResponse> getProducts() {
		
		return productRepository.findAll()
				.stream()
				.map(p -> new ProductResponse(p.getId(),p.getName(),p.getPrice()))
				.toList();
    }
	
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable Long id) {
		/* リクエストされたProductを取得 */
		Product findedProduct = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		
		/* Entity→DTO */
		return new ProductResponse(
					findedProduct.getId(),
					findedProduct.getName(),
					findedProduct.getPrice()
				);
	}
	
	/* 商品登録 */
	@PostMapping
	public ProductResponse createProduct(@RequestBody ProductCreateRequest request) {
		Product product = new Product(
				null,
				request.name(),
				request.price());
		Product saved = productRepository.save(product);
		return new ProductResponse(
				saved.getId(),
				saved.getName(),
				saved.getPrice()
				);
	}
	
	@PutMapping("/{id}")
	public ProductResponse updateProduct(
			@PathVariable Long id,
			@RequestBody ProductUpdateRequest updated
			) {
		/* リクエストのproductを取得 */
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		/* Entity→DTO */
		product.setName(updated.name());
		product.setPrice(updated.price());
		
		Product saved = productRepository.save(product);
		
		return new ProductResponse(
				saved.getId(),
				saved.getName(),
				saved.getPrice()
				);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		productRepository.deleteById(id);
	}
	
}
