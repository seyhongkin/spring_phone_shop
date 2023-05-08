package com.infinite.solution.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.dto.ColorDTO;
import com.infinite.solution.phoneshop.entity.Color;
import com.infinite.solution.phoneshop.exceptions.ResourceNotFoundException;
import com.infinite.solution.phoneshop.mapper.ColorMapper;
import com.infinite.solution.phoneshop.repository.ColorRepository;
import com.infinite.solution.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ColorServiceImpl implements ColorService{
	private final ColorRepository colorRepository;
	private final ColorMapper colorMapper;
	
	
	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("color", id));
	}


	@Override
	public Color create(ColorDTO dto) {
		Color color = colorMapper.toColor(dto);
		return colorRepository.save(color);
	}

}
