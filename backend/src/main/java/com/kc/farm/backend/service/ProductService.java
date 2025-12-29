package com.kc.farm.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kc.farm.backend.dto.ProductCreateRequest;
import com.kc.farm.backend.dto.ProductResponse;
import com.kc.farm.backend.dto.ProductUpdateRequest;

/** Controllerがよぶ窓口 */
@Service
public interface ProductService {

	List<ProductResponse> findAll();
	
	ProductResponse findById(Long id);
	
	ProductResponse create(ProductCreateRequest request);
	
	ProductResponse update(Long id, ProductUpdateRequest request);
	
	void delete(Long id);
	
}
