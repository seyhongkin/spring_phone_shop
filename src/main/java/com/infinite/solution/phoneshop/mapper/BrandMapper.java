package com.infinite.solution.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.infinite.solution.phoneshop.dto.BrandDTO;
import com.infinite.solution.phoneshop.entity.Brand;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	
	Brand toBrand(BrandDTO dto);
	BrandDTO toBrandDTO(Brand entity);
}
