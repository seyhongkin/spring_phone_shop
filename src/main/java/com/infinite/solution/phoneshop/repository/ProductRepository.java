package com.infinite.solution.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinite.solution.phoneshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
}
