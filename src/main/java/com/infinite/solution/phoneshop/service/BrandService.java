package com.infinite.solution.phoneshop.service;

import com.infinite.solution.phoneshop.dto.BrandDTO;
import com.infinite.solution.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer Id);
	Brand update(Integer Id, Brand brand);
}
