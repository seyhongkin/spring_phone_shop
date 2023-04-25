package com.infinite.solution.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.spec.BrandSpec;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand>{
	
	List<Brand> findByNameContainingIgnoreCase(String name);
}
