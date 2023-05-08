package com.infinite.solution.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.solution.phoneshop.dto.ProductDTO;
import com.infinite.solution.phoneshop.entity.Product;
import com.infinite.solution.phoneshop.mapper.ProductMapper;
import com.infinite.solution.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {
	private final ProductService productService;
	private final ProductMapper productMapper;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO productDto){
		Product product = productMapper.toProduct(productDto);
		product = productService.create(product);
		return ResponseEntity.ok(product);
	}
}
