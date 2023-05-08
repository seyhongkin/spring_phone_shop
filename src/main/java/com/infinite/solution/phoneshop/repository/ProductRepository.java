package com.infinite.solution.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinite.solution.phoneshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
