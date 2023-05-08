package com.infinite.solution.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.infinite.solution.phoneshop.dto.ProductDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.service.ColorService;
import com.infinite.solution.phoneshop.service.ModelService;

@Mapper(uses = {ModelService.class, ColorService.class}, componentModel = "spring")
public interface ProductMapper {
	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	Product toProduct(ProductDTO productDTO);
	
	@Mapping(target = "modelId", source = "model.id")
	@Mapping(target = "colorId", source = "color.id")
	ProductDTO productToDTO(Product product); 
}
