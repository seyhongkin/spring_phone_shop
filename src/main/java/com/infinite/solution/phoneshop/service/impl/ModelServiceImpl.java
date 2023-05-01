package com.infinite.solution.phoneshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.entity.Model;
import com.infinite.solution.phoneshop.repository.ModelRepository;
import com.infinite.solution.phoneshop.service.BrandService;
import com.infinite.solution.phoneshop.service.ModelService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService{
	private ModelRepository modelRepository;
	private BrandService brandService;
	
	@Override
	public Model create(Model model) {
		Integer brandId = model.getBrand().getId();
		brandService.getById(brandId);
		return modelRepository.save(model);
	}

}
