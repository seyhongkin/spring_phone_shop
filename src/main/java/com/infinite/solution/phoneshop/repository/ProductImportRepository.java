package com.infinite.solution.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinite.solution.phoneshop.entity.ProductImportHistory;

public interface ProductImportRepository extends JpaRepository<ProductImportHistory, Long>{

}