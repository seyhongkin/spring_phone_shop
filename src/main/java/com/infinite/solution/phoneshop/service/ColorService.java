package com.infinite.solution.phoneshop.service;

import com.infinite.solution.phoneshop.dto.ColorDTO;
import com.infinite.solution.phoneshop.entity.Color;

public interface ColorService {
	Color getById(Long id);
	Color create(ColorDTO dto);
}
