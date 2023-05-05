package com.infinite.solution.phoneshop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.entity.Model;
import com.infinite.solution.phoneshop.exceptions.ResourceNotFoundException;
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

	@Override
	public Model getById(Integer id) {
		return modelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Model", id));
	}

	/* @TODO Fix this
	@Override
	public Model update(Integer id, ModelDTO modelDto) {
		Model model = getById(id);
		model.setName(modelDto.getName());
		model.setId(modelDto.getBrandId());
		return modelRepository.save(model);
	}

	@Override
	public void remove(Integer id) {
		Model model = getById(id);
		modelRepository.delete(model);
	}
	*/
	@Override
	public List<Model> getModelsByBrandId(Integer brandId) {
		return modelRepository.findByBrandId(brandId);
	}
	
	
}
