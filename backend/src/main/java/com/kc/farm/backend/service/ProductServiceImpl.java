package com.kc.farm.backend.service;

import java.util.List;

import com.kc.farm.backend.dto.ProductCreateRequest;
import com.kc.farm.backend.dto.ProductResponse;
import com.kc.farm.backend.dto.ProductUpdateRequest;
import com.kc.farm.backend.entity.Product;
import com.kc.farm.backend.repository.ProductRepository;

/** ProductRepositoryの各種メソッドを実行する */
public class ProductServiceImpl implements ProductService {
	ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<ProductResponse> findAll() {
		return productRepository.findAll()
					.stream()
					.map(ProductResponse::from)
					.toList();
	}
	
	@Override
	public ProductResponse findById(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("not found Product"));
		
		return ProductResponse.from(product);
	}
	
	@Override
	public ProductResponse create(ProductCreateRequest request) {
		Product product = new Product(
				null,
				request.name(),
				request.price());
		Product saved = productRepository.save(product);
		return ProductResponse.from(saved);
	}
	
	@Override
	public ProductResponse update(Long id, ProductUpdateRequest request) {
		/* リクエストのproductを取得 */
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		/* Entity→DTO */
		product.setName(request.name());
		product.setPrice(request.price());
		
		/* 実行 */
		Product saved = productRepository.save(product);
		
		return ProductResponse.from(saved);
	}
	
	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
