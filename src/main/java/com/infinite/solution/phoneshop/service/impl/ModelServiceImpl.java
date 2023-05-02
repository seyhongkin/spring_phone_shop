package com.infinite.solution.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.entity.Model;
import com.infinite.solution.phoneshop.repository.ModelRepository;
import com.infinite.solution.phoneshop.service.ModelService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService{
	private ModelRepository modelRepository;
	
	@Override
	public Model create(Model model) {
		return modelRepository.save(model);
	}

}
