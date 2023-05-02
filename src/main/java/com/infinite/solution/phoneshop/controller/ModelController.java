package com.infinite.solution.phoneshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO dto){
		Model model = modelMapper.toModel(dto);
		model = modelService.create(model);
		return ResponseEntity.ok(modelMapper.toModelDto(model));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer modelId){
		Model model = modelService.getById(modelId);
		return ResponseEntity.ok(modelMapper.toModelDto(model));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer modelId,@RequestBody ModelDTO modelDTO){
		Model updateModel = modelService.update(modelId, modelDTO);
		return ResponseEntity.ok(modelMapper.toModelDto(updateModel));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Integer modelId){
		modelService.remove(modelId);
		return ResponseEntity.ok(modelId + " deleted successful!");
	}
}
