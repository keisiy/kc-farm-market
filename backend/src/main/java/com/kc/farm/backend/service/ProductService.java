package com.kc.farm.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kc.farm.backend.record.Product;

@Service
public class ProductService {

	public List<Product> findAll() {
        return List.of(
            new Product(1L, "トマト", 300),
            new Product(2L, "きゅうり", 200),
            new Product(3L, "なす", 250)
        );
    }
	
}
