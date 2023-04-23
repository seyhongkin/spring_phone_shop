package com.infinite.solution.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinite.solution.phoneshop.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
	List<Brand> findByNameContainingIgnoreCase(String name);
}
