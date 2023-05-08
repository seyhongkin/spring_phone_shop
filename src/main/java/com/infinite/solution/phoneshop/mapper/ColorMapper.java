package com.infinite.solution.phoneshop.mapper;

import org.mapstruct.Mapper;

import com.infinite.solution.phoneshop.dto.ColorDTO;
import com.infinite.solution.phoneshop.entity.Color;

@Mapper(componentModel = "spring")
public interface ColorMapper {
	Color toColor(ColorDTO colorDTO);
}
