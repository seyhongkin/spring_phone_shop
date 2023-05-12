package com.infinite.solution.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.dto.ProductImportDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.entity.ProductImportHistory;
import com.infinite.solution.phoneshop.exceptions.ResourceNotFoundException;
import com.infinite.solution.phoneshop.mapper.ProductMapper;
import com.infinite.solution.phoneshop.repository.ProductImportRepository;
import com.infinite.solution.phoneshop.repository.ProductRepository;
import com.infinite.solution.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductImportRepository productImportRepository;

	@Override
	public Product create(Product product) {
		String color = product.getColor().getName();
		String model = product.getModel().getName();
		product.setName("%s %s".formatted(model, color));
		return productRepository.save(product);
	}
	
	@Override
	public Product getById(Long id) {
		return productRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product",id));
	}

	@Override
	public void importProduct(ProductImportDTO importHistoryDto) {
		//update product stock unit
		Product product = getById(importHistoryDto.getProductId());
		Integer availableUnit = 0;
		if(product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		availableUnit = availableUnit + importHistoryDto.getImportUnit();
		product.setAvailableUnit(availableUnit);
		productRepository.save(product);
		
		//save import history
		ProductImportHistory importHistory = productMapper.toProductImportHistory(importHistoryDto, product);
		productImportRepository.save(importHistory);
	}


}
