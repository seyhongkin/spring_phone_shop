package com.infinite.solution.phoneshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.infinite.solution.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long Id);
	Brand update(Long Id, Brand brand);
	//List<Brand> getBrands(); NO NEED TO USE THIS
	List<Brand> getBrands(String name);
	//List<Brand> getBrands(Map<String, String> param);
	Page<Brand> getBrands(Map<String, String> param);
}
