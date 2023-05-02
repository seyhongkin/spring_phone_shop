package com.infinite.solution.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.infinite.solution.phoneshop.dto.ModelDTO;
import com.infinite.solution.phoneshop.entity.Brand;
import com.infinite.solution.phoneshop.entity.Model;
import com.infinite.solution.phoneshop.service.BrandService;

@Mapper(uses = {BrandService.class}, componentModel = "spring")
public interface ModelMapper {
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
	
	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);
	
	@Mapping(target = "brandId", source = "model.brand.id")
	ModelDTO toModelDto(Model model);
	
	/*
	default Brand toBrand(Integer brId) {
		Brand brand = new Brand();
		brand.setId(brId);
		return brand;
	}
	*/
}
