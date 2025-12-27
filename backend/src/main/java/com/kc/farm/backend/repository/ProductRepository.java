package com.kc.farm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.farm.backend.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
