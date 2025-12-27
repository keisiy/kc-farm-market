package com.kc.farm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.farm.backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
