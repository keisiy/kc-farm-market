package com.kc.farm.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kc.farm.backend.record.ProductResponse;

@Service
public class ProductService {

	public List<ProductResponse> findAll() {
        return List.of(
            new ProductResponse(1L, "トマト", 300),
            new ProductResponse(2L, "きゅうり", 200),
            new ProductResponse(3L, "なす", 250)
        );
    }
	
}
