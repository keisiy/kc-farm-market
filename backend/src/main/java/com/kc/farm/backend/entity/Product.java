package com.kc.farm.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    protected Product() {} // JPA用

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /* getterだけ用意 */
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
	
}