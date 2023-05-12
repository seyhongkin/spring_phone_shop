package com.infinite.solution.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.infinite.solution.phoneshop.dto.ProductDTO;
import com.infinite.solution.phoneshop.dto.ProductImportDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.entity.ProductImportHistory;
import com.infinite.solution.phoneshop.service.ColorService;
import com.infinite.solution.phoneshop.service.ModelService;

@Mapper(uses = {ModelService.class, ColorService.class}, componentModel = "spring")
public interface ProductMapper {
	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	@Mapping(target = "availableUnit", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "imagePath", ignore = true)
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "salePrice", ignore = true)
	Product toProduct(ProductDTO productDTO);
	
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "importDate", ignore = true)
	ProductImportHistory toProductImportHistory(ProductImportDTO importDTO, Product product);
	
//	@Mapping(target = "modelId", source = "model.id")
//	@Mapping(target = "colorId", source = "color.id")
//	ProductDTO productToDTO(Product product); 
}
