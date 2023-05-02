package com.infinite.solution.phoneshop.service;

import com.infinite.solution.phoneshop.dto.ModelDTO;
import com.infinite.solution.phoneshop.entity.Model;


public interface ModelService {
	Model create(Model model);
	Model getById(Integer id);
	Model update(Integer id, ModelDTO modelDto);
	void remove(Integer id);
}
