package com.infinite.solution.phoneshop.service;

import java.util.List;
import java.util.Map;

import com.infinite.solution.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer Id);
	Brand update(Integer Id, Brand brand);
	//List<Brand> getBrands(); NO NEED TO USE THIS
	List<Brand> getBrands(String name);
	List<Brand> getBrands(Map<String, String> param);
}
