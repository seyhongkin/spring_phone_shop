package com.infinite.solution.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.repository.ProductRepository;
import com.infinite.solution.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public Product create(Product product) {
		String color = product.getColor().getName();
		String model = product.getModel().getName();
		product.setName("%s %s".formatted(model, color));
		return productRepository.save(product);
	}

}
