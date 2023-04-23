package com.infinite.solution.phoneshop.service;

import java.util.List;

import com.infinite.solution.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer Id);
	Brand update(Integer Id, Brand brand);
	List<Brand> getBrands();
	List<Brand> getBrands(String name);
}
