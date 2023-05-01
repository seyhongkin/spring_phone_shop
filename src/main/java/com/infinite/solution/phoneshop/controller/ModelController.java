package com.infinite.solution.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.solution.phoneshop.dto.ModelDTO;
import com.infinite.solution.phoneshop.entity.Model;
import com.infinite.solution.phoneshop.mapper.ModelMapper;
import com.infinite.solution.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {
	private final ModelService modelService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO dto){
		Model model = ModelMapper.INSTANCE.toModel(dto);
		model = modelService.create(model);
		return ResponseEntity.ok(model);
	}
}
