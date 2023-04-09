package com.infinite.solution.phoneshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.repository.BrandRepository;
import com.infinite.solution.phoneshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandRepository brandRepository; 
	
	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}

}
