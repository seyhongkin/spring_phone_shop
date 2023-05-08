package com.infinite.solution.phoneshop.service;

import java.util.List;

import com.infinite.solution.phoneshop.entity.Model;


public interface ModelService {
	Model create(Model model);
	Model getById(Long id);
	//Model update(Integer id, ModelDTO modelDto);
	//void remove(Integer id);
	List<Model> getModelsByBrandId(Long brandId);
}
