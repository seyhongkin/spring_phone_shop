package com.infinite.solution.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infinite.solution.phoneshop.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
	
}
